package com.bawei.mall.cart.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 切换SKU实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckSkuRequest {
    @NotNull(message = "商品Id不可以为空")
    private Long productId;

    @NotNull(message = "商品旧SKU不能为空")
    private String oldSku;

    @NotNull(message = "商品新SKU不可为空")
    private String newSku;

}
