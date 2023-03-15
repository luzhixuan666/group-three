package com.bawei.mall.cart.domain.request;

import com.bawei.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 添加购物车
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddCarRequest extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 商品ID
     */
    @NotNull(message = "添加购物车商品ID不能为空")
    private Long productId;
    /**
     * 商品SKU
     */
    @NotNull(message = "添加购物车SKU不能为空")
    private String productSku;
    /**
     * 商品数量
     */
    @NotNull(message = "添加购物车商品数量不能为空")
    @Min(value = 0 )
    private Long count;
}
