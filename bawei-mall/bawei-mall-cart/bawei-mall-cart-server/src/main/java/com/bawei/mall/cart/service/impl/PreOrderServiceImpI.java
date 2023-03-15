package com.bawei.mall.cart.service.impl;

import com.bawei.common.core.utils.uuid.IdUtils;
import com.bawei.common.redis.service.RedisService;
import com.bawei.common.security.utils.SecurityUtils;
import com.bawei.mall.cart.domain.CartInfo;
import com.bawei.mall.cart.domain.respone.PreOrderRespone;
import com.bawei.mall.cart.service.IPreOrderSercice;
import com.bawei.common.core.utils.KeyUtils;
import com.bawei.mall.order.domain.OrderInfo;
import com.bawei.mall.order.domain.OrderItemInfo;
import com.bawei.mall.order.remote.RemoteOrder;
import com.bawei.mall.product.cache.ProductInfoCache;
import com.bawei.mall.product.domain.MallProductSkuInfo;
import com.bawei.mall.product.domain.reponse.ProductDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class PreOrderServiceImpI implements IPreOrderSercice {
    @Autowired
    private RedisService redisService;
    @Autowired
    private ProductInfoCache productInfoCache;
    @Autowired
    private RemoteOrder remoteOrder;


    @Override
    public PreOrderRespone<OrderInfo, OrderItemInfo> preOrder() {
        //获取当前用户的Id
        Long userId = SecurityUtils.getUserId();
        //获取购物车缓存的KEY
        String carKey = KeyUtils.getCarKey(userId);
        //从缓存中获取购物车列表
        Set<String> carProductKeyList = redisService.getCacheMapKeySet(carKey);
        //使用流操作对购物车列表每一个项进行处理
        List<OrderItemInfo> orderItemInfoList =carProductKeyList.stream()
                .map(carProductKey->
                        //将购物车中的商品KEY和对应的值转换为购物车对象，从而获得商品ID、商品SKU、商品数量、是否勾选等信息。
                    CartInfo.carProductModelBuild(carProductKey,
                            redisService.getCacheMapValue(carKey, carProductKey)))
                //过滤出所有被勾选的商品
                .filter(cartInfo -> "Y".equals(cartInfo.getCheck()))
                .map(cartInfo -> {
                    //从缓存中获取商品信息
                    ProductDetailsResponse productDetailsResponse = productInfoCache.get(cartInfo.getProductId());
                    //根据商品SKU列表获取购物车商品SKU信息
                    Optional<MallProductSkuInfo> productSkuInfoOptional = productDetailsResponse.getSkuList()
                            .stream()
                            //过滤出与购物车信息中的商品SKU相匹配的信息
                            .filter(skuInfo -> cartInfo.getProductSku().equals(skuInfo.getSku()))
                            .findFirst();
                    //计算商品单价，没过滤出来就赋值0
                    BigDecimal unitPrice = BigDecimal.ZERO;
                    //如果存在，则从匹配的对象中中获取价格并赋值单价
                    if (productSkuInfoOptional.isPresent()){
                        unitPrice=productSkuInfoOptional.get().getPrice();
                    }
                    //计算商品总价
                    BigDecimal sumPrice = unitPrice.multiply(new BigDecimal(cartInfo.getCount())).setScale(2,BigDecimal.ROUND_HALF_UP);;
                    //创建订单详情对象，将购物车中信息赋值到订单详情中
                    OrderItemInfo itemInfo = OrderItemInfo.builder()
                            .productId(cartInfo.getProductId())
                            .productSku(cartInfo.getProductSku())
                            .count(cartInfo.getCount())
                            .unitPrice(unitPrice)
                            .sumPrice(sumPrice)
                            .payPrice(sumPrice.multiply(new BigDecimal(0.8)))//折扣我是直接统一打八折
                            .isPay("N")
                            .status("create").build();
                    //商品信息和商品SKU信息作为订单项的参数存储
                    itemInfo.setParam("productInfo",productDetailsResponse.getProduct());
                    itemInfo.setParam("productSku",cartInfo.getProductSku());
                    return itemInfo;
                }).collect(Collectors.toList());
        //创建一个订单信息对象
        OrderInfo.OrderInfoBuilder orderInfoBuilder = OrderInfo.builder();
        //计算出购买的总数并赋值订单对象中
        long count = orderItemInfoList.stream()
                .map(OrderItemInfo::getCount)//将每个订单明细对象映射为其数量
                .reduce(0L, (a, b) -> a + b)//将这些数量相加
                .longValue();
        orderInfoBuilder.count(count);
        //计算总价并赋值
        BigDecimal sumPrice = orderItemInfoList.stream()
                .map(OrderItemInfo::getSumPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2,BigDecimal.ROUND_HALF_UP);
        orderInfoBuilder.sumPrice(sumPrice);
        //计算出支付价格并赋值
        BigDecimal payPrice = orderItemInfoList.stream()
                .map(OrderItemInfo::getPayPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2,BigDecimal.ROUND_HALF_UP);;
        orderInfoBuilder.payPrice(payPrice);
        //计算出优惠价格并赋值
        orderInfoBuilder.discountPrice(sumPrice.subtract(payPrice)
                .setScale(2,BigDecimal.ROUND_HALF_UP));
        //赋值一个订单token
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        orderInfoBuilder.orderToken(token);
        redisService.redisTemplate.opsForValue().set("order:token"+userId,token,5, TimeUnit.MINUTES);
        OrderInfo orderInfo = orderInfoBuilder.build();
        //将订单列表和订单明细列表存储到Redis缓存中
        redisService.setCacheObject(KeyUtils.getPreOrderKey(userId),orderInfo);
        redisService.setCacheObject(KeyUtils.getPreOrderItemKey(userId),orderItemInfoList);
        //构建一个预结算的对象，包括订单信息和订单项列表
        PreOrderRespone<OrderInfo, OrderItemInfo> preOrderRespone =
                new PreOrderRespone<OrderInfo, OrderItemInfo>(){{
                    setOrderInfo(orderInfo);
                    setOrderItemInfo(orderItemInfoList);
                }};
        return preOrderRespone;
    }

    @Override
    public void checkAddress(Long addressId) {
        Long userId = SecurityUtils.getUserId();
        String preOrderKey = KeyUtils.getPreOrderKey(userId);
        OrderInfo orderInfo = redisService.getCacheObject(preOrderKey);
        orderInfo.setAddressId(addressId);
        redisService.setCacheObject(preOrderKey,orderInfo);
    }
}
