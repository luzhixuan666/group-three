package com.bawei.mall.cart.domain.respone;

import com.bawei.mall.cart.domain.model.CarInfoRespModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单预结算
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreOrderRespone<T,I> {
    /**
     * 订单信息
     */
    private T orderInfo;

    /**
     * 订单详情
     */
    private List<I> orderItemInfo;
}
