package com.bawei.mall.order.service;

import java.util.List;
import com.bawei.mall.order.domain.OrderInfo;

/**
 * 订单Service接口
 *
 * @author LuZhiXuan
 * @date 2023-02-09
 */
public interface IOrderInfoService
{
    /**
     * 查询订单
     *
     * @param id 订单主键
     * @return 订单
     */
    public OrderInfo selectOrderInfoById(Long id);

    /**
     * 查询订单列表
     *
     * @param orderInfo 订单
     * @return 订单集合
     */
    public List<OrderInfo> selectOrderInfoList(OrderInfo orderInfo);

    /**
     * 新增订单
     *
     * @param
     * @return 结果
     */
    public void generateOrderInfo();

    /**
     * 修改订单
     *
     * @param orderInfo 订单
     * @return 结果
     */
    public void updateOrderInfo(OrderInfo orderInfo);

    /**
     * 批量删除订单
     *
     * @param ids 需要删除的订单主键集合
     * @return 结果
     */
    public int deleteOrderInfoByIds(Long[] ids);

    /**
     * 删除订单信息
     *
     * @param id 订单主键
     * @return 结果
     */
    public int deleteOrderInfoById(Long id);


    OrderInfo selectOrderInfoByNumber(String number);

    /**
     * 获取token
     * @param uid
     * @return
     */
    String generateToken(Long uid);
}
