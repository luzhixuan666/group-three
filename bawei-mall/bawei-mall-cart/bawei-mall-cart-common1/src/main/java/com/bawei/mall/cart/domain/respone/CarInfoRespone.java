package com.bawei.mall.cart.domain.respone;

import com.bawei.mall.cart.domain.CartInfo;
import com.bawei.mall.cart.domain.model.CarInfoRespModel;
import com.bawei.mall.product.domain.reponse.ProductDetailsResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarInfoRespone {

    private List<CarInfoRespModel> carInfoRespModelList;

    private BigDecimal sumPrice;
}
