package com.bawei.mall.cart.controller;

import com.bawei.common.core.domain.R;
import com.bawei.mall.cart.service.IPreOrderSercice;
import org.springframework.web.bind.annotation.*;

/**
 * 预结算订单
 */
@RestController
@RequestMapping("/pre")
public class PreOrderController {

    private final IPreOrderSercice preOrderSercice;


    public PreOrderController(IPreOrderSercice iPreOrderSercice) {
        this.preOrderSercice = iPreOrderSercice;
    }


    /**
     * 选择收货地址
     * @param addressId
     * @return
     */
    @PutMapping("/change/address/{addressId}")
    public R checkAddress(@PathVariable Long addressId){
        preOrderSercice.checkAddress(addressId);
        return R.ok();
    }


    //订单预结算
    @PostMapping("/order")
    public R preOrder(){
        return R.ok(preOrderSercice.preOrder());
    }
}
