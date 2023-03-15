package com.bawei.mall.order.factory;

import com.bawei.common.core.domain.R;
import com.bawei.common.core.web.domain.AjaxResult;
import com.bawei.common.core.web.page.TableDataInfo;
import com.bawei.mall.order.domain.OrderInfo;
import com.bawei.mall.order.domain.OrderItemInfo;
import com.bawei.mall.order.remote.RemoteOrder;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
@Log4j2
public class RemoteOrderFactory implements FallbackFactory<RemoteOrder> {

    @Override
    public RemoteOrder create(Throwable cause) {
        log.error("订单微服务远程调用异常",cause);
        return new RemoteOrder() {

            @Override
            public R getInfo(Long id) {
                return R.fail(String.format("远程调用发生异常:{}",cause.getMessage()));
            }

            @Override
            public TableDataInfo list(OrderInfo orderInfo) {
                return null;
            }

            @Override
            public R edit(OrderInfo orderInfo) {
                return R.fail(String.format("远程调用发生异常:{}",cause.getMessage()));
            }

            @Override
            public R getInfoNumber(String number) {
                return R.fail(String.format("远程调用发生异常:{}",cause.getMessage()));
            }



        };
    }
}
