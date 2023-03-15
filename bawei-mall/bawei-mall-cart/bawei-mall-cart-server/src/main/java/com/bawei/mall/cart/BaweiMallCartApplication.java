package com.bawei.mall.cart;

import com.bawei.common.security.annotation.EnableCustomConfig;
import com.bawei.common.security.annotation.EnableRyFeignClients;
import com.bawei.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
@ComponentScan(value = "com.bawei.mall")
public class BaweiMallCartApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaweiMallCartApplication.class,args);
    }
}
