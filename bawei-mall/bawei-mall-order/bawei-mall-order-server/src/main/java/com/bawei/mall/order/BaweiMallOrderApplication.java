package com.bawei.mall.order;

import com.bawei.common.security.annotation.EnableCustomConfig;
import com.bawei.common.security.annotation.EnableRyFeignClients;
import com.bawei.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class BaweiMallOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaweiMallOrderApplication.class,args);
        System.out.println("(♥◠‿◠)ﾉﾞ  商城 - 订单模块启动成功   ლ(´ڡ`ლ)ﾞ  ");
    }
}
