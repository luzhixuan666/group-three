package com.bawei.mall.product.controller;

import com.bawei.common.core.domain.R;
import com.bawei.common.rabbit.domain.Message;
import com.bawei.common.rabbit.enums.QueueEnum;
import com.bawei.mall.product.cache.ProductInfoCache;
import com.bawei.mall.product.domain.reponse.ProductDetailsResponse;
import com.bawei.mall.product.domain.reponse.ProductInfoResponse;
import com.bawei.mall.product.service.IMallProductInfoService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author LuZhixuan
 * @description:
 * @Date 2022-10-19 下午 02:46
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ProductInfoCache productInfoCache;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private IMallProductInfoService productInfoService;

    @GetMapping("/{id}")
    public R get(@PathVariable Long id){
        ProductDetailsResponse productDetailsResponse = productInfoCache.get(id);
        return R.ok(productDetailsResponse);
    }

    @GetMapping("/refreshData/{id}")
    private R refreshData(@PathVariable Long id){
        return R.ok(productInfoCache.refreshData(id));
    }

    @PostMapping("/sendMsg/{msg}")
    public R sendMsg(@PathVariable("msg") String msg){


//        ProductInfoResponse productInfoResponse = productInfoService.selectMallProductInfoById(10L);
        rabbitTemplate.convertAndSend(QueueEnum.PRODUCT_ADD.queueName(),
                Message.builderMsg(11L));
        return R.ok();
    }
}
