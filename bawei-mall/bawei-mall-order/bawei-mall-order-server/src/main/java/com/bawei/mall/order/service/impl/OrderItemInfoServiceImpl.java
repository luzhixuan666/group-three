package com.bawei.mall.order.service.impl;

import java.util.List;

import com.bawei.mall.order.domain.OrderItemInfo;
import com.bawei.mall.order.mapper.OrderItemInfoMapper;
import com.bawei.mall.order.service.IOrderItemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单明细Service业务层处理
 *
 * @author Luzhixuan
 * @date 2023-02-09
 */
@Service
public class OrderItemInfoServiceImpl implements IOrderItemInfoService
{
    @Autowired
    private OrderItemInfoMapper orderItemInfoMapper;

    /**
     * 查询订单明细
     *
     * @param id 订单明细主键
     * @return 订单明细
     */
    @Override
    public OrderItemInfo selectOrderItemInfoById(Long id)
    {
        return orderItemInfoMapper.selectOrderItemInfoById(id);
    }

    /**
     * 查询订单明细列表
     *
     * @param orderItemInfo 订单明细
     * @return 订单明细
     */
    @Override
    public List<OrderItemInfo> selectOrderItemInfoList(OrderItemInfo orderItemInfo)
    {
        return orderItemInfoMapper.selectOrderItemInfoList(orderItemInfo);
    }

    /**
     * 新增订单明细
     *
     * @param orderItemInfo 订单明细
     * @return 结果
     */
    @Override
    public int insertOrderItemInfo(OrderItemInfo orderItemInfo)
    {
        return orderItemInfoMapper.insertOrderItemInfo(orderItemInfo);
    }

    /**
     * 修改订单明细
     *
     * @param orderItemInfo 订单明细
     * @return 结果
     */
    @Override
    public void updateOrderItemInfo(OrderItemInfo orderItemInfo)
    {
         orderItemInfoMapper.updateOrderItemInfo(orderItemInfo);
    }

    /**
     * 批量删除订单明细
     *
     * @param ids 需要删除的订单明细主键
     * @return 结果
     */
    @Override
    public int deleteOrderItemInfoByIds(Long[] ids)
    {
        return orderItemInfoMapper.deleteOrderItemInfoByIds(ids);
    }

    /**
     * 删除订单明细信息
     *
     * @param id 订单明细主键
     * @return 结果
     */
    @Override
    public int deleteOrderItemInfoById(Long id)
    {
        return orderItemInfoMapper.deleteOrderItemInfoById(id);
    }

    @Override
    public void batchInstallOrderItemInfo(List<OrderItemInfo> orderItemInfoList) {
        orderItemInfoMapper.batchInstallOrderItemInfo(orderItemInfoList);
    }
}
