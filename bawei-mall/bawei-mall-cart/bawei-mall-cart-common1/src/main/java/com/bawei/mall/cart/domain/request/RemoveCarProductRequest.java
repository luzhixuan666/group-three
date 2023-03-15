package com.bawei.mall.cart.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RemoveCarProductRequest {
    @NotNull(message = "商品不可为空")
    public Long productId;

    @NotNull(message = "商品规格不可为空")
    private String productSku;

}
