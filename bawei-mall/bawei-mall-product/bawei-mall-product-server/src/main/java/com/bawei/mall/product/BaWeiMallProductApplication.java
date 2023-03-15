package com.bawei.mall.product;

import com.bawei.common.security.annotation.EnableCustomConfig;
import com.bawei.common.security.annotation.EnableRyFeignClients;
import com.bawei.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 商品模块
 *
 * @author Luzhixuan
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
@EnableScheduling
@EnableRetry
@EnableTransactionManagement
public class BaWeiMallProductApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(BaWeiMallProductApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  商城 - 商品模块启动成功   ლ(´ڡ`ლ)ﾞ  ");
    }
}
