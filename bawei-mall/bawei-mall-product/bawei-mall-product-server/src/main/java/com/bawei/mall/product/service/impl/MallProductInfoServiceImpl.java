package com.bawei.mall.product.service.impl;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import com.bawei.common.core.exception.ServiceException;
import com.bawei.common.core.utils.DateUtils;
import com.bawei.common.core.utils.KeyUtils;
import com.bawei.common.core.utils.StringUtils;
import com.bawei.common.core.utils.ThreadPoolUtils;
import com.bawei.common.core.utils.bean.BeanUtils;
import com.bawei.common.lock.local.LocalUtils;
import com.bawei.common.lock.ression.service.RedissonService;
import com.bawei.common.rabbit.domain.Message;
import com.bawei.common.rabbit.enums.QueueEnum;
import com.bawei.common.redis.service.RedisService;
import com.bawei.common.security.utils.SecurityUtils;
import com.bawei.mall.order.domain.OrderItemInfo;
import com.bawei.mall.product.cache.ProductInfoCache;
import com.bawei.mall.product.domain.MallProductRuleInfo;
import com.bawei.mall.product.domain.MallProductSkuInfo;
import com.bawei.mall.product.domain.model.ProductModel;
import com.bawei.mall.product.domain.model.SkuModel;
import com.bawei.mall.product.domain.reponse.ProductDetailsResponse;
import com.bawei.mall.product.domain.reponse.ProductInfoResponse;
import com.bawei.mall.product.domain.request.ProductInfoRequest;
import com.bawei.mall.product.service.IMallProductRuleInfoService;
import com.bawei.mall.product.service.IMallProductSkuInfoService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.bawei.mall.product.mapper.MallProductInfoMapper;
import com.bawei.mall.product.domain.MallProductInfo;
import com.bawei.mall.product.service.IMallProductInfoService;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

/**
 * 商品信息Service业务层处理
 *
 * @author Luzhixuan
 * @date 2023-02-30
 */
@Service
@Transactional
public class MallProductInfoServiceImpl implements IMallProductInfoService,Runnable
{
    @Autowired
    private MallProductInfoMapper mallProductInfoMapper;

    @Autowired
    private IMallProductSkuInfoService skuInfoService;

    @Autowired
    private IMallProductRuleInfoService ruleInfoService;

    @Autowired
    private RedisService redisService;


    @Autowired
    private ProductInfoCache productInfoCache;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RedissonService redissonService;

    @Autowired
    private IMallProductSkuInfoService productSkuInfoService;

    Jedis jedis = new Jedis("106.14.46.21", 6379);

//    @Scheduled(cron = "0/5 * * * * ? ")
    @Scheduled(cron = "0 0 0-2 * * ? ")
    public void MathHostScore(){
        //查询所有商品信息
        List<MallProductInfo> list=mallProductInfoMapper.findAll();
        for (MallProductInfo productInfo : list) {
            Long id = productInfo.getId();
            MallProductInfo mallProductInfo = mallProductInfoMapper.selectMallProductInfoById(id);
            Long collectCount = mallProductInfo.getCollectCount();
            Long commentCount = mallProductInfo.getCommentCount();
            long totalNum = commentCount + collectCount;
            String finaltotalNum = String.valueOf(totalNum);
            //存入redis
            jedis.hset("host_count",String.valueOf(id),finaltotalNum);
            jedis.close();
        }
        //获取
        Map<String, String> hostCount = jedis.hgetAll("host_count");
        //计算热度指标
        HashMap<String, Double> map = new HashMap<>();
        for (Map.Entry<String,String> entry:hostCount.entrySet()){
            String key = entry.getKey();
            int viewNum = Integer.parseInt(entry.getValue());
            //获取每个品牌的创建时间
            MallProductInfo mallProductInfo = mallProductInfoMapper.selectMallProductInfoById(Long.valueOf(key));
            Date createTime= mallProductInfo.getCreateTime();
            Date date = new Date();
            //hacker news的排名算法计算热度值
            double pow = Math.pow((date.getHours() - createTime.getHours() + 2), 1.8);
            double host = (viewNum - 1) / pow;
            map.put(key,host);
        }
        //存储热度指标redis
        jedis.zadd("host_final",map);
        jedis.close();
    }

    /**
     * 查询商品信息
     *
     * @param id 商品信息主键
     * @return 商品信息
     */
    @Override
    public ProductInfoResponse selectMallProductInfoById(Long id)
    {
        MallProductInfo mallProductInfo = mallProductInfoMapper.selectMallProductInfoById(id);
        ProductInfoResponse productInfoResponse = new ProductInfoResponse();
        BeanUtils.copyBeanProp(productInfoResponse, mallProductInfo);
        productInfoResponse.setSkuInfoList(
                skuInfoService.selectMallProductSkuInfoList(new MallProductSkuInfo(){{
                    setProductId(id);
                }})
        );
        return productInfoResponse;
    }

    @Override
    public ProductDetailsResponse selectProductDetailsById (Long productId) {
        if (productId == null || productId == 0){
            throw new ServiceException("查询商品信息，依据不合法！");
        }
        ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse();
        ProductModel productModel = mallProductInfoMapper.selectProductModelById(productId);
        if (productModel == null){
            throw new ServiceException("查询商品信息，商品数据为空");
        }
        productDetailsResponse.setProduct(productModel);
        List<MallProductSkuInfo> mallProductSkuInfos = skuInfoService.selectMallProductSkuInfoList(productId);
        if (mallProductSkuInfos == null || mallProductSkuInfos.size() == 0){
            throw new ServiceException("查询商品信息，SKU数据为空");
        }
        productDetailsResponse.setSkuList(mallProductSkuInfos);
        MallProductRuleInfo ruleInfo = ruleInfoService.selectMallProductRuleInfoById(productModel.getRuleId());
        if (ruleInfo == null){
            throw new ServiceException("查询商品信息，规格数据为空");
        }
        productDetailsResponse.setProductRule(ruleInfo);
        return productDetailsResponse;
    }

    /**
     * 查询商品信息列表
     *
     * @param mallProductInfo 商品信息
     * @return 商品信息
     */
    @Override
    public List<MallProductInfo> selectMallProductInfoList(MallProductInfo mallProductInfo)
    {
        List<MallProductInfo> list = mallProductInfoMapper.selectMallProductInfoList(mallProductInfo);
        return list;
    }




    /**
     * 新增商品信息
     *
     * @param productInfoRequest 商品信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertMallProductInfo(ProductInfoRequest productInfoRequest)
    {
        productInfoRequest.setCreateBy(String.valueOf(SecurityUtils.getUserId()));
        productInfoRequest.setCreateTime(DateUtils.getNowDate());
        int i = mallProductInfoMapper.insertMallProductInfo(productInfoRequest);
        if (i == 0){
            return i;
        }
        skuInfoService.batchInsertProductSku(
                SkuModel.builderSkuModel(productInfoRequest.getId(), productInfoRequest.getSkuInfoList())
        );
        // 给搜索系统发送消息需要进行搜索更新
        rabbitTemplate.convertAndSend(QueueEnum.PRODUCT_ADD.queueName(),
                Message.builderMsg(productInfoRequest.getId()));
        return i;
    }

    /**
     * 修改商品信息
     *
     * @param productInfoRequest 商品信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateMallProductInfo(ProductInfoRequest productInfoRequest)
    {
        productInfoRequest.setUpdateBy(String.valueOf(SecurityUtils.getUserId()));
        productInfoRequest.setUpdateTime(DateUtils.getNowDate());
        int i = mallProductInfoMapper.updateMallProductInfo(productInfoRequest);
        if (i == 0){
            return i;
        }
        skuInfoService.deleteMallProductSkuInfoByProductId(productInfoRequest.getId());
        skuInfoService.batchInsertProductSku(
                SkuModel.builderSkuModel(productInfoRequest.getId(), productInfoRequest.getSkuInfoList())
        );
        return i;
    }

    /**
     * 批量删除商品信息
     *
     * @param ids 需要删除的商品信息主键
     * @return 结果
     */
    @Override
    public int deleteMallProductInfoByIds(Long[] ids)
    {
        skuInfoService.deleteMallProductSkuInfoByProductIds(ids);
        for (Long id : ids) {
            // 延迟执行
            productInfoCache.delayRemove(id);
        }
        return mallProductInfoMapper.deleteMallProductInfoByIds(ids);
    }

    /**
     * 删除商品信息信息
     *
     * @param id 商品信息主键
     * @return 结果
     */
    @Override
    public int deleteMallProductInfoById(Long id)
    {
        return mallProductInfoMapper.deleteMallProductInfoById(id);
    }

    /**
     * 商品总条数
     * @param mallProductInfo 商品查询
     * @return
     */
    @Override
    public Long selectMallProductInfoCount (MallProductInfo mallProductInfo) {
        return mallProductInfoMapper.selectMallProductInfoCount(mallProductInfo);
    }


    @Override
    public void lockProductStock() {
        Long userId = SecurityUtils.getUserId();
        //根据用户Id生成预订单明细的Id，作为订单明细缓存的KEY
        String preOrderItemKey = KeyUtils.getPreOrderItemKey(userId);
        //根据KEY从Redis缓存中获取当前用户的购物车商品列表
        List<OrderItemInfo> orderItemInfoList= redisService.getCacheObject(preOrderItemKey);
        //设置一个加锁的时候的KEY
        String lockKey = "stock";
        //stream对购物车列表进行操作，将所有的键值对存入新的字符串列表
        List<String> keyValueList= orderItemInfoList.stream()
                .map(orderItemInfo ->
                        //根据购物车列表中的每个商品的SKU和Id生成唯一字符串键值对，用于后续的分布式锁
                        KeyUtils.getCarProductKey(orderItemInfo.getProductId(),orderItemInfo.getProductSku()))
                .collect(Collectors.toList());
        for (String keyValue : keyValueList){
            //首先使用本地锁进行加锁
            boolean lockStatus = LocalUtils.lock(lockKey, keyValue, 3, 5000);
            //加锁失败返回异常信息
            if (!lockStatus){
                throw new ServiceException("锁的异常中断");
            }
        }
        //遍历所有的字符串键值
        for (String keyValue : keyValueList) {
            try {
                //使用 Redisson分布式锁进行加锁,防止并发操作导致库存不一致
                redissonService.lock(lockKey,keyValue,3000,3,5000);
            }catch (Exception e){
                //布式锁加锁失败，列表中的所有键值对所对应的锁进行解锁操作
               keyValueList.stream().forEach(keyVal->{
                   LocalUtils.unlock(lockKey,keyVal);
                   redissonService.unlock(lockKey,keyValue);
               });
               throw new RuntimeException(e);
            }
        }
        //遍历当前用户的购物车列表
        orderItemInfoList.forEach(orderItemInfo -> {
            //通过商品 ID 获取商品的详细信息（包括 SKU 列表等）
            ProductDetailsResponse productDetailsResponse = productInfoCache.get(orderItemInfo.getProductId());
            //然后在 SKU 列表中查找对应的 SKU
            Optional<MallProductSkuInfo> skuInfoDetails = productDetailsResponse.getSkuList().stream()
                    //过滤流中元素，保留满足条件的元素信息
                    .filter(productSkuInfo -> orderItemInfo.getProductSku().equals(productSkuInfo.getSku()))
                    .findFirst();
            //如果对象不存在,释放锁，抛出异常
            if (!skuInfoDetails.isPresent()){
                keyValueList.stream().forEach(keyVal->{
                    LocalUtils.unlock(lockKey,keyVal);
                    redissonService.unlock(lockKey,keyVal);
                });
                throw new ServiceException(StringUtils.format("商品【{}】下规格【{}】，参数不正确",productDetailsResponse.getProduct().getId(),
                        orderItemInfo.getProductSku()));
            }
            //从对象中获取SKU信息并存储到商品SKU信息对象
            MallProductSkuInfo productSkuInfo=skuInfoDetails.get();
            //如果库存不足，抛出库存不足异常
            if (productSkuInfo.getStock()==null||productSkuInfo.getStock()<orderItemInfo.getCount()){
                throw new ServiceException(StringUtils.format("商品【{}】下规格【{}】，库存不足",productDetailsResponse.getProduct().getId(),
                        orderItemInfo.getProductSku()));
            }
        });
        //遍历当前商户的购物车列表
        orderItemInfoList.forEach(orderItemInfo -> {
            //将当前用户购物车列表中的商品信息赋值给商品SKU对象，表示需要扣除库存的商品SKU信息
            MallProductSkuInfo productSkuInfo = MallProductSkuInfo.builder().productId(orderItemInfo.getProductId())
                    .sku(orderItemInfo.getProductSku())
                    .stock(orderItemInfo.getCount()).build();
            //扣减库存
            productSkuInfoService.deductionStock(productSkuInfo);
        });
        //更新 Redis 缓存和数据库中的库存数据。
        orderItemInfoList.stream().map(orderItemInfo ->
                orderItemInfo.getProductId())
                .forEach(productId->productInfoCache.refreshData(productId));
        //释放之前获取的本地锁和 Redisson 锁，确保商品库存已经成功扣除。
        keyValueList.stream().forEach(keyVal->{
            LocalUtils.unlock(lockKey,keyVal);
            redissonService.unlock(lockKey,keyVal);
        });
    }

    @Override
    public void run() {

    }
}
