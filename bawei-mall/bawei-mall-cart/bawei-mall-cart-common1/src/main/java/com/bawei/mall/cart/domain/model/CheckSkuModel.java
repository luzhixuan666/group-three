package com.bawei.mall.cart.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 选择SKU业务模型对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckSkuModel {

    private Long productId;


    private String oldSku;


    private String newSku;

}
