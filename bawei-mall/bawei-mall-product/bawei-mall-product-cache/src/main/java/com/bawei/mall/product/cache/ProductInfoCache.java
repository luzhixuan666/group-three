package com.bawei.mall.product.cache;

import com.bawei.cache.annotation.CacheRole;
import com.bawei.cache.db.BaseDatabaseCache;
import com.bawei.common.core.domain.R;
import com.bawei.common.core.exception.ServiceException;
import com.bawei.common.core.utils.SpringUtils;
import com.bawei.common.core.utils.StringUtils;
import com.bawei.common.core.utils.reflect.ReflectUtils;
import com.bawei.common.redis.service.RedisService;
import com.bawei.mall.product.domain.reponse.ProductDetailsResponse;
import com.bawei.mall.product.remote.RemoteProductInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author LuZhixuan
 * @description: 商品缓存
 */
@Component
public class ProductInfoCache implements BaseDatabaseCache<Long, ProductDetailsResponse> {

    /**
     * 日志
     */
    private final static Logger log = LoggerFactory.getLogger(ProductInfoCache.class);

    private static final String keyPre = "product:info:";

    /**
     * 缓存判断是否在本服务的依据
     * 如果为null则表示不在本服务
     * 如果有值，则表示在本服务
     */
    private static Class<?> clazz = null;

    static {
        try {
            clazz = Class.forName("com.bawei.mall.product.service.impl.MallProductInfoServiceImpl");
        } catch (ClassNotFoundException e) {
            log.info("缓存启动，不是在本服务当中");
        }
    }

    /**
     * 商品的业务层
     */
    private Object productInfoService;

    private Object getProductInfoService(){
        if (productInfoService == null){
            productInfoService = SpringUtils.getBean(clazz);
        }
        return productInfoService;
    }

    /**
     * 缓存对象
     */
    private final RedisService redisService;

    @Autowired
    private RemoteProductInfo remoteProductInfo;

    public ProductInfoCache (RedisService redisService) {
        this.redisService = redisService;
    }

    /**
     * 根据数据ID拼接缓存key
     * @param key 数据Id
     * @return
     */
    @Override
    public String getKey (Long key) {
        if (key == null){
            throw new ServiceException("商品缓存key非法");
        }
        return keyPre + key.toString();
    }

    /**
     * 存储缓存
     * @param key 键
     * @param val 值
     * @return
     */
    @Override
    @CacheRole(serverName = "mall-product")
    public boolean put (Long key, ProductDetailsResponse val) {
        try {
            redisService.setCacheObject(getKey(key), val);
        }catch (Exception e){
            log.error("商品缓存存储异常key:[{}],val:[{}]", key , val , e);
            return false;
        }
        return true;
    }

    /**
     * 删除缓存
     * @param key 键
     * @return
     */
    @Override
    @CacheRole(serverName = "mall-product")
    public boolean remove (Long key) {
        log.info("删除数据：[{}]", getKey(key));
        return redisService.deleteObject(getKey(key));
    }

    /**
     * 获取缓存
     * @param key 键
     * @return
     */
    @Override
    public ProductDetailsResponse get (Long key) {
        ProductDetailsResponse productDetailsResponse = redisService.getCacheObject(getKey(key));
        // 如果为 空则需要去数据库去数据
        if (productDetailsResponse == null){
            productDetailsResponse = this.getData(key);
            // 防止击穿
            this.put(key,productDetailsResponse == null ?
                    new ProductDetailsResponse() : productDetailsResponse);
        }
        return productDetailsResponse;
    }

    /**
     * 操作缓存 一定有两种情况 ！！！
     * 缓存在本服务
     * 缓存在其他服务
     * @param key 数据ID
     * @return
     */
    @Override
    @CacheRole(serverName = "mall-product")
    public ProductDetailsResponse getData (Long key) {
        // 如果clazz是空的话，表示我需要走的feign调用
        // 如果clazz不是空的话，我是不是需要走本服务
        if (clazz != null){
            Object productInfoService = getProductInfoService();
            if (productInfoService == null){
                log.error("商品缓存获取失败key:[{}]",key);
                throw new ServiceException(StringUtils.format("商品缓存获取失败key:[{}]",key));
            }
            ProductDetailsResponse productDetailsResponse = ReflectUtils.invokeMethodByName(productInfoService,
                    "selectProductDetailsById",
                    new Object[]{key});
            log.info("商品缓存，从数据库获取信息成功key:[{}]，val:[{}]", key, productDetailsResponse);
            return productDetailsResponse;
        }else {
            R<ProductDetailsResponse> productResponse = remoteProductInfo.getProductResponse(key);
            if (productResponse.isError()){
                log.error("商品缓存，远程调用获取信息失败key:[{}]，调用结果：[{}]", key, productResponse);
                return null;
            }
            log.info("商品缓存，远程调用获取信息成功key:[{}]，val:[{}]", key, productResponse.getData());
            return productResponse.getData();
        }
    }

    /**
     * 刷新缓存
     * @param key
     * @return
     */
    @Override
    @CacheRole(serverName = "mall-product")
    public boolean refreshData (Long key) {
        return this.put(key, this.getData(key));
    }
}
