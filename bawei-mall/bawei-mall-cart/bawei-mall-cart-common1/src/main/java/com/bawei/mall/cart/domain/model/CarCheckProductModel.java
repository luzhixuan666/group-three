package com.bawei.mall.cart.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.sl.draw.DrawNotImplemented;

/**
 * 选中
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarCheckProductModel {
    /**
     * Id
     */
    private Long productId;
    /**
     * 规格
     */
    private String productSku;

    /**
     * 是否选中
     */
    private String check;
}
