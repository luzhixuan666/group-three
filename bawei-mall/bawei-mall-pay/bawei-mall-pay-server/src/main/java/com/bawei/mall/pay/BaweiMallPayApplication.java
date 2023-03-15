package com.bawei.mall.pay;

import com.bawei.common.security.annotation.EnableCustomConfig;
import com.bawei.common.security.annotation.EnableRyFeignClients;
import com.bawei.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 商品模块
 *
 * @author Luzhixuan
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class BaweiMallPayApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(BaweiMallPayApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  商城 - 支付模块启动成功   ლ(´ڡ`ლ)ﾞ  ");
    }
}
