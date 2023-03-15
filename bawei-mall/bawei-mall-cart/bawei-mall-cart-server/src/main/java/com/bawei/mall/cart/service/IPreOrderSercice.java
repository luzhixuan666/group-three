package com.bawei.mall.cart.service;

import com.bawei.mall.cart.domain.respone.PreOrderRespone;
import com.bawei.mall.order.domain.OrderInfo;
import com.bawei.mall.order.domain.OrderItemInfo;

public interface IPreOrderSercice {

    PreOrderRespone<OrderInfo, OrderItemInfo> preOrder();

    /**
     * 选择收货地址
     * @param addressId
     */
    void checkAddress(Long addressId);



}
