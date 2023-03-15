package com.bawei.mall.order.remote;

import com.bawei.common.core.constant.ServiceNameConstants;
import com.bawei.common.core.domain.R;
import com.bawei.common.core.web.domain.AjaxResult;
import com.bawei.common.core.web.page.TableDataInfo;
import com.bawei.mall.order.domain.OrderInfo;
import com.bawei.mall.order.domain.OrderItemInfo;
import com.bawei.mall.order.factory.RemoteOrderFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(contextId = "remoteOrder",value = ServiceNameConstants.ORDER_SERVICE,
        path = "/info",fallbackFactory = RemoteOrderFactory.class)
@Component
public interface RemoteOrder {
    /**
     * 查询订单详情表
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public R<OrderInfo>  getInfo(@PathVariable("id") Long id);

    /**
     * 查看订单列表
     * @param orderInfo
     * @return
     */
    @GetMapping("/list")
    public TableDataInfo list(OrderInfo orderInfo);


    /**
     * 修改订单
     */
    @PutMapping
    public R edit(@RequestBody OrderInfo orderInfo);


    /**
     * 查询订单
     */
    @GetMapping(value = "/findbyNumber/{number}")
    public R<OrderInfo> getInfoNumber(@PathVariable("number") String number);



}
