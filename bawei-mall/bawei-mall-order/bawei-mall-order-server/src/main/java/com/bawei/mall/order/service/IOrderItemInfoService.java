package com.bawei.mall.order.service;

import java.util.List;
import com.bawei.mall.order.domain.OrderItemInfo;

/**
 * 订单明细Service接口
 *
 * @author Luzhixuan
 * @date 2023-02-09
 */
public interface IOrderItemInfoService
{
    /**
     * 查询订单明细
     *
     * @param id 订单明细主键
     * @return 订单明细
     */
    public OrderItemInfo selectOrderItemInfoById(Long id);

    /**
     * 查询订单明细列表
     *
     * @param orderItemInfo 订单明细
     * @return 订单明细集合
     */
    public List<OrderItemInfo> selectOrderItemInfoList(OrderItemInfo orderItemInfo);

    /**
     * 新增订单明细
     *
     * @param orderItemInfo 订单明细
     * @return 结果
     */
    public int insertOrderItemInfo(OrderItemInfo orderItemInfo);

    /**
     * 修改订单明细
     *
     * @param orderItemInfo 订单明细
     * @return 结果
     */
    public void updateOrderItemInfo(OrderItemInfo orderItemInfo);

    /**
     * 批量删除订单明细
     *
     * @param ids 需要删除的订单明细主键集合
     * @return 结果
     */
    public int deleteOrderItemInfoByIds(Long[] ids);

    /**
     * 删除订单明细信息
     *
     * @param id 订单明细主键
     * @return 结果
     */
    public int deleteOrderItemInfoById(Long id);

    void batchInstallOrderItemInfo(List<OrderItemInfo> orderItemInfoList);
}
