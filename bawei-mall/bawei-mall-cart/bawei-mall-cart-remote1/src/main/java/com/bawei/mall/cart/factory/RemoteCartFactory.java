package com.bawei.mall.cart.factory;

import com.bawei.common.core.domain.R;
import com.bawei.mall.cart.remote.RemoteCart;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;

@Log4j2
public class RemoteCartFactory implements FallbackFactory<RemoteCart> {
    @Override
    public RemoteCart create(Throwable cause) {
        log.error("购物车远程调用服务发生异常",cause);
        return new RemoteCart() {
            @Override
            public R clearCarchangeProduct() {
                return R.fail(String.format("远程调用发生异常:{}",cause.getMessage()));
            }
        };
    }
}
