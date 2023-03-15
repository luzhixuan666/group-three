package com.bawei.mall.order;

import com.bawei.common.core.context.SecurityContextHolder;
import com.bawei.mall.order.service.IOrderInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class OrderInfoTest {

    @Autowired
    private IOrderInfoService orderInfoService;

    @Test
    private void generateOrderInfo(){
        SecurityContextHolder.setUserId("1");
        orderInfoService.generateOrderInfo();
    }
}
