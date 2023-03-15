package com.bawei.mall.order.mapper;

import java.util.List;
import com.bawei.mall.order.domain.OrderInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 订单Mapper接口
 *
 * @author LuZhiXuan
 * @date 2023-02-09
 */
public interface OrderInfoMapper
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
     * @param orderInfo 订单
     * @return 结果
     */
    public int insertOrderInfo(OrderInfo orderInfo);

    /**
     * 修改订单
     *
     * @param orderInfo 订单
     * @return 结果
     */
    public void updateOrderInfo(OrderInfo orderInfo);

    /**
     * 删除订单
     *
     * @param id 订单主键
     * @return 结果
     */
    public int deleteOrderInfoById(Long id);

    /**
     * 批量删除订单
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderInfoByIds(Long[] ids);

    /**
     * 根据订单查询
     * @param
     */
    OrderInfo selectOrderInfoByNumber(@Param("number") String number);
}
