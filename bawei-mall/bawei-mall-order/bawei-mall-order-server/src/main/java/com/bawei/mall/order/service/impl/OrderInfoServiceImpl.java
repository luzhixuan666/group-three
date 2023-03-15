package com.bawei.mall.order.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.bawei.common.core.domain.R;
import com.bawei.common.core.exception.ServiceException;
import com.bawei.common.core.utils.KeyUtils;
import com.bawei.common.core.utils.uuid.IdUtils;
import com.bawei.common.redis.service.RedisService;
import com.bawei.common.security.utils.SecurityUtils;
import com.bawei.mall.cart.remote.RemoteCart;
import com.bawei.mall.order.mapper.OrderInfoMapper;
import com.bawei.mall.order.service.IOrderInfoService;
import com.bawei.mall.order.domain.OrderItemInfo;
import com.bawei.mall.order.service.IOrderItemInfoService;
import com.bawei.mall.product.remote.RemoteProductInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;
import com.bawei.mall.order.domain.OrderInfo;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单Service业务层处理
 *
 * @author LuZhiXuan
 * @date 2023-02-09
 */
@Service
@Log4j2
public class OrderInfoServiceImpl implements IOrderInfoService
{
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    private final RedisService redisService;

    public OrderInfoServiceImpl(RedisService redisService) {
        this.redisService = redisService;
    }

    @Autowired
    private RemoteProductInfo remoteProductInfo;

    @Autowired
    private IOrderItemInfoService orderItemInfoService;

    @Autowired
    private RemoteCart remoteCart;

    @Autowired
    private static final String ID_TOKEN_ORDER="user_token";

    /**
     * 查询订单
     *
     * @param id 订单主键
     * @return 订单
     */
    @Override
    public OrderInfo selectOrderInfoById(Long id)
    {
        return orderInfoMapper.selectOrderInfoById(id);
    }

    /**
     * 查询订单列表
     *
     * @param orderInfo 订单
     * @return 订单
     */
    @Override
    public List<OrderInfo> selectOrderInfoList(OrderInfo orderInfo)
    {
        return orderInfoMapper.selectOrderInfoList(orderInfo);
    }

    /**
     * 新增订单
     *
     * @param
     * @return 结果
     */
    @Override
    @Transactional
    public void generateOrderInfo() {
        //获取当前用户Id
        Long userId = SecurityUtils.getUserId();
            //根据用户id获取到订单的KEY和订单明细的KEY
        String preOrderKey = KeyUtils.getPreOrderKey(userId);
        String preOrderItemKey = KeyUtils.getPreOrderItemKey(userId);
            //根据预订单的KEY获取到缓存中对应的订单和订单明细
        OrderInfo orderInfo = redisService.getCacheObject(preOrderKey);
        List<OrderItemInfo> orderItemInfoList = redisService.getCacheObject(preOrderItemKey);
        //验证令牌
        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
        String token = orderInfo.getOrderToken();
        //原子验证令牌 删除令牌
        Long result = (Long) redisService.redisTemplate.execute(
                new DefaultRedisScript<>(script,Long.class), Arrays.asList("order:token"+userId,token),token);
        System.out.println(result);
        if (result==0){
            throw new ServiceException("请勿重复提交");
        }
            //库存校验并锁定库存
            R lockProductStock = remoteProductInfo.lockProductStock();
            if (lockProductStock.isError()) {
                log.error("生成订单发生异常", lockProductStock.getMsg());
                throw new ServiceException("库存不足");
            }
            //通过雪花算法生成订单主键ID
            Long orderId = IdUtils.flakeId();
            //通过UUID生成订单编号
            String orderNumber = IdUtils.fastSimpleUUID();
            //将上述信息赋值给订单对象和订单明细列表
            orderInfo.setId(orderId);
            orderInfo.setNumber(orderNumber);
            orderInfo.setCreateBy(String.valueOf(userId));
            orderInfo.setCreateTime(new Date());
            orderInfo.setStatus("1");
            orderItemInfoList.forEach(orderItemInfo -> {
                orderItemInfo.setOrderId(orderId);
                orderItemInfo.setOrderNumber(orderNumber);
            });
            //落库，将订单和订单明细数据添加到数据库
            orderInfoMapper.insertOrderInfo(orderInfo);
            orderItemInfoService.batchInstallOrderItemInfo(orderItemInfoList);
            //落库后，删除购物车选中的商品
            remoteCart.clearCarchangeProduct();
            log.info(orderInfo);
            log.info(orderItemInfoList);

    }

    /**
     * 修改订单
     *
     * @param orderInfo 订单
     * @return 结果
     */
    @Override
    public void updateOrderInfo(OrderInfo orderInfo)
    {
        orderInfoMapper.updateOrderInfo(orderInfo);
    }

    /**
     * 批量删除订单
     *
     * @param ids 需要删除的订单主键
     * @return 结果
     */
    @Override
    public int deleteOrderInfoByIds(Long[] ids)
    {
        return orderInfoMapper.deleteOrderInfoByIds(ids);
    }

    /**
     * 删除订单信息
     *
     * @param id 订单主键
     * @return 结果
     */
    @Override
    public int deleteOrderInfoById(Long id)
    {
        return orderInfoMapper.deleteOrderInfoById(id);
    }

    @Override
    public  OrderInfo selectOrderInfoByNumber(String number) {
       return orderInfoMapper.selectOrderInfoByNumber(number);
    }

    /**
     * 获取token接口
     * @param uid
     * @return
     */
    @Override
    public String generateToken(Long uid) {
        String token = UUID.randomUUID().toString().replaceAll("-","");
        String key = ID_TOKEN_ORDER+ token;
        redisService.redisTemplate.opsForValue()
                .set(key,String.valueOf(uid),5, TimeUnit.MINUTES);
        return token;
    }

    /**
     * 验证 Token 正确性
     * @param token token 字符串
     * @param value value 存储在Redis中的辅助验证信息
     * @return 验证结果
     */
    public boolean validToken(String token, String value) {
        // 设置 Lua 脚本，其中 KEYS[1] 是 key，KEYS[2] 是 value
        String script = "if redis.call('get', KEYS[1]) == KEYS[2] then return redis.call('del', KEYS[1]) else return 0 end";
        RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
        // 根据 Key 前缀拼接 Key
        Long userId = SecurityUtils.getUserId();
        String key = ID_TOKEN_ORDER + userId;
        // 执行 Lua 脚本
        Long result = (Long) redisService.redisTemplate.execute(redisScript, Arrays.asList(key, value));
        System.out.println(result);
        // 根据返回结果判断是否成功成功匹配并删除 Redis 键值对，若果结果不为空和0，则验证通过
        if (result != null && result != 0L) {
            log.info("验证 token={},key={},value={} 成功", token, key, value);
            return true;
        }
        log.info("验证 token={},key={},value={} 失败", token, key, value);
        return false;
    }

}
