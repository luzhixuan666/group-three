package com.bawei.mall.cart.domain.model;

import com.bawei.mall.cart.domain.CartInfo;
import com.bawei.mall.product.domain.reponse.ProductDetailsResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 购物车单挑信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarInfoRespModel {

    private CartInfo cartInfo;

    private ProductDetailsResponse productDetailsResponse;

    private BigDecimal productPrice;

}
