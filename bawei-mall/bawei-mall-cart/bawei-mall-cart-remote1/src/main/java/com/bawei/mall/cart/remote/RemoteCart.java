package com.bawei.mall.cart.remote;

import com.bawei.common.core.constant.ServiceNameConstants;
import com.bawei.common.core.domain.R;
import com.bawei.mall.cart.factory.RemoteCartFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;

@FeignClient(contextId = "remoteCart",value = ServiceNameConstants.CART_SERVICE,
 path = "/cart",fallbackFactory = RemoteCartFactory.class)
@Component
public interface RemoteCart {

    @DeleteMapping("/clear")
    public R clearCarchangeProduct();
}
