package com.bawei.mall.cart.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 选中购物车
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarProductCheckRequest {

    /**
     * Id
     */
    @NotNull(message = "商品信息不能为空")
    private Long productId;
    /**
     * 规格
     */
    @NotNull(message = "商品规格不能为空")
    private String productSku;

    /**
     * 是否选中
     */
    @NotNull(message = "商品是否选中不能为空")
    private String check;

}
