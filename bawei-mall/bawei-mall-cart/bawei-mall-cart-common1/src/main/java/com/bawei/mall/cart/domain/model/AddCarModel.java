package com.bawei.mall.cart.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 购物车添加模块
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddCarModel {
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 商品SKU
     */
    private String productSku;
    /**
     * 商品数量
     */
    private Long count;
}
