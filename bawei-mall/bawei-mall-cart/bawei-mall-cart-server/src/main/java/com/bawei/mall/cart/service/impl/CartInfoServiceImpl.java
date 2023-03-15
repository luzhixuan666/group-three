package com.bawei.mall.cart.service.impl;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import com.bawei.common.core.utils.DateUtils;
import com.bawei.common.redis.service.RedisService;
import com.bawei.common.security.utils.SecurityUtils;
import com.bawei.mall.cart.CarProductCheckEnum;
import com.bawei.common.core.constant.CarConstants;
import com.bawei.mall.cart.domain.model.*;
import com.bawei.mall.cart.domain.respone.CarInfoRespone;
import com.bawei.common.core.utils.KeyUtils;
import com.bawei.mall.order.domain.OrderInfo;
import com.bawei.mall.product.cache.ProductInfoCache;
import com.bawei.mall.product.domain.MallProductSkuInfo;
import com.bawei.mall.product.domain.reponse.ProductDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bawei.mall.cart.mapper.CartInfoMapper;
import com.bawei.mall.cart.domain.CartInfo;
import com.bawei.mall.cart.service.ICartInfoService;

/**
 * 购物车Service业务层处理
 *
 * @author Luzhixuan
 * @date 2023-01-30
 */
@Service
public class CartInfoServiceImpl implements ICartInfoService
{
    @Autowired
    private CartInfoMapper cartInfoMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private ProductInfoCache productInfoCache;

    /**
     * 查询购物车
     *
     * @param id 购物车主键
     * @return 购物车
     */
    @Override
    public CartInfo selectCartInfoById(Long id)
    {
        return cartInfoMapper.selectCartInfoById(id);
    }

    /**
     * 查询购物车列表
     *
     * @param cartInfo 购物车
     * @return 购物车
     */
    @Override
    public List<CartInfo> selectCartInfoList(CartInfo cartInfo)
    {
        return cartInfoMapper.selectCartInfoList(cartInfo);
    }

    /**
     * 新增购物车
     *
     * @param cartInfo 购物车
     * @return 结果
     */
    @Override
    public int insertCartInfo(CartInfo cartInfo)
    {
        cartInfo.setCreateTime(DateUtils.getNowDate());
        cartInfo.setCreateById(SecurityUtils.getUserId());
        return cartInfoMapper.insertCartInfo(cartInfo);
    }

    /**
     * 修改购物车
     *
     * @param cartInfo 购物车
     * @return 结果
     */
    @Override
    public int updateCartInfo(CartInfo cartInfo)
    {
        return cartInfoMapper.updateCartInfo(cartInfo);
    }

    /**
     * 批量删除购物车
     *
     * @param
     * @return 结果
     */
    @Override
    public int deleteCartInfo(List<RemoveCarProductModel> removeCarProductModelList)
    {

        Long userId = SecurityUtils.getUserId();
        String carKey = KeyUtils.getCarKey(userId);
        List<Long> ids = removeCarProductModelList.stream().
                map(removeCarProductModel -> {
                    String carProductKey =
                            KeyUtils.getCarProductKey(removeCarProductModel.getProductId(), removeCarProductModel.getProductSku());
                    CarProductModel carProductModel = redisService.getCacheMapValue(carKey, carProductKey);
                    redisService.deleteCacheMapValue(carKey,carProductKey);
                    return carProductModel.getCarInfoId();
                }).collect(Collectors.toList());
        return cartInfoMapper.deleteCartInfoByIds(ids);
    }

    /**
     * 删除购物车信息
     *
     * @param id 购物车主键
     * @return 结果
     */
    @Override
    public int deleteCartInfoById(Long id)
    {
        return cartInfoMapper.deleteCartInfoById(id);
    }

    @Override
    public void addCar(AddCarModel addCarModel) {
        Long userId = SecurityUtils.getUserId();
        String carKey = KeyUtils.getCarKey(userId);
        String carProductKey = KeyUtils.getCarProductKey(addCarModel.getProductId(), addCarModel.getProductSku());
        //从redis获取查看是否有缓存
        CarProductModel carProductModel = redisService.getCacheMapValue(carKey, carProductKey);
        if (carProductModel==null){
            //添加
            CartInfo cartInfo=CartInfo.builder()
                    .check(CarProductCheckEnum.NO_CHECK.code())
                    .productId(addCarModel.getProductId())
                    .productSku(addCarModel.getProductSku())
                    .count(addCarModel.getCount()).build();
            cartInfo.setCreateById(userId);
            cartInfo.setCreateTime(new Date());
            this.cartInfoMapper.insertCartInfo(cartInfo);
            //缓存
            carProductModel=CarProductModel.builder()
                    .carInfoId(cartInfo.getId())
                    .count(addCarModel.getCount())
                    .createTime(cartInfo.getCreateTime())
                    .check(cartInfo.getCheck()).build();
            this.redisService.setCacheMapValue(carKey,carProductKey,carProductModel);
        }else {
            //累加
            Long productCount=carProductModel.getCount() + addCarModel.getCount();
            CartInfo cartInfo=CartInfo.builder().count(productCount).id(carProductModel.getCarInfoId()).build();
            this.cartInfoMapper.updateCartInfo(cartInfo);
            //添加数据库
            carProductModel.setCount(productCount);
        }
        this.redisService.setCacheMapValue(carKey,carProductKey,carProductModel);
    }

    /**
     * 查看购物车所有信息
     */
    @Override
    public CarInfoRespone cartInfoAllList() {
        Long userId = SecurityUtils.getUserId();
        String carKey = KeyUtils.getCarKey(userId);
        Set<String> carProductKeySet = this.redisService.getCacheMapKeySet(carKey);
        List<CarInfoRespModel> carInfoRespModels= carProductKeySet.stream()
                .map(carProductKey -> {
                        CarProductModel carProductModel=  redisService.getCacheMapValue(carKey, carProductKey);
                        String[] split = carProductKey.split(CarConstants.CAR_PRODUCT_KEY);
                        CartInfo cartInfo=CartInfo.builder().id(carProductModel.getCarInfoId())
                                .count(carProductModel.getCount())
                                .check(carProductModel.getCheck())
                                .productId(Long.valueOf(split[0]))
                                .productSku(split[1]).build();
                    ProductDetailsResponse productDetailsResponse = productInfoCache.get(Long.valueOf(split[0]));
                    Optional<MallProductSkuInfo> skuInfoOptional = productDetailsResponse.getSkuList().stream()
                            .filter(skuInfo -> skuInfo.getSku().equals(split[1]))
                            .findFirst();
                    BigDecimal productPrice = BigDecimal.ZERO;
                    if (skuInfoOptional.isPresent()){
                        productPrice= skuInfoOptional.get().getPrice();
                    }
                    BigDecimal productSumPrice = productPrice.multiply(new BigDecimal(cartInfo.getCount()));
                    return CarInfoRespModel.builder().cartInfo(cartInfo)
                            .productDetailsResponse(productDetailsResponse)
                            .productPrice(productSumPrice).build();
                }).collect(Collectors.toList());
        BigDecimal sumPrice=carInfoRespModels.stream()
                .filter(carInfoRespModel -> CarProductCheckEnum.CHECK.code().equals(carInfoRespModel.getCartInfo().getCheck()))
                .map(CarInfoRespModel::getProductPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return CarInfoRespone.builder()
                .carInfoRespModelList(carInfoRespModels)
                .sumPrice(sumPrice).build();
    }

    @Override
    public void carCheckProduct(CarCheckProductModel carCheckProductModel) {
        Long userId = SecurityUtils.getUserId();
        String carKey = KeyUtils.getCarKey(userId);

        String carProductKey = KeyUtils.getCarProductKey(carCheckProductModel.getProductId(),
                carCheckProductModel.getProductSku());
        CarProductModel carProductModel = redisService.getCacheMapValue(carKey, carProductKey);

        carProductModel.setCheck(carCheckProductModel.getCheck());

        CartInfo cartInfo=CartInfo.builder().id(carProductModel.getCarInfoId())
                .check(carProductModel.getCheck()).build();
        this.cartInfoMapper.updateCartInfo(cartInfo);

        redisService.setCacheMapValue(carKey,carProductKey,carProductModel);

    }

    @Override
    public void checkSku(CheckSkuModel checkSkuModel) {
        Long userId = SecurityUtils.getUserId();
        String carKey = KeyUtils.getCarKey(userId);
        String oldCarProductKey =
                KeyUtils.getCarProductKey(checkSkuModel.getProductId(), checkSkuModel.getOldSku());
        String newCarProductKey =
                KeyUtils.getCarProductKey(checkSkuModel.getProductId(), checkSkuModel.getNewSku());
        CarProductModel newCarProductModel =
                redisService.getCacheMapValue(carKey, newCarProductKey);
        //通过旧的SKUkey过去旧的数量
        CarProductModel oldCarProductModel=
                redisService.getCacheMapValue(carKey,oldCarProductKey);
        if (newCarProductModel == null){
            Long carInfoId = oldCarProductModel.getCarInfoId();
            //修改数量
            this.cartInfoMapper.updateCartInfo(
              CartInfo.builder()
                      .id(carInfoId).productSku(checkSkuModel.getNewSku())
                      .build()
            );
            //创建新的skukey并且存储cartInfo信息
            this.redisService.setCacheMapValue(carKey,newCarProductKey,oldCarProductModel);
            //在缓存删除旧的skuKey信息
            this.redisService.deleteCacheMapValue(carKey,oldCarProductKey);
        }else {
            //数量累加
            newCarProductModel.setCount(newCarProductModel.getCount()+oldCarProductModel.getCount());
            //修改mysql中新的sku的数量
            this.cartInfoMapper.updateCartInfo(
                    CartInfo.builder()
                            .id(newCarProductModel.getCarInfoId())
                            .count(newCarProductModel.getCount())
                            .build()
            );
            //修改缓存
            this.redisService.setCacheMapValue(carKey,newCarProductKey,newCarProductModel);
            //删除数据库
            this.cartInfoMapper.deleteCartInfoById(oldCarProductModel.getCarInfoId());
        }
        //在缓存删除旧的skyKey信息
        this.redisService.deleteCacheMapValue(carKey,oldCarProductKey);
    }

    @Override
    public void updateSkuCount(UpdateSkuCountModel updateSkuCountModel) {
        Long userId = SecurityUtils.getUserId();
        String carKey = KeyUtils.getCarKey(userId);
        String carProductKey =
                KeyUtils.getCarProductKey(updateSkuCountModel.productId, updateSkuCountModel.getProductSku());
        CarProductModel carProductModel=this.redisService.getCacheMapValue(carKey,carProductKey);
        carProductModel.setCount(updateSkuCountModel.getCount());
        this.cartInfoMapper.updateCartInfo(
                CartInfo.builder().id(carProductModel.getCarInfoId()).count(carProductModel.getCount()).build()
        );
        //刷新缓存

        this.redisService.setCacheMapValue(carKey,carProductKey,carProductModel);
    }

    /**
     * 清除购物车选中的商品
     */
    @Override
    public void clearCarChangeProduct() {
        Long userId = SecurityUtils.getUserId();
        //根据对应的用户ID获取购物车的KEY
        String carKey = KeyUtils.getCarKey(userId);
        //根据KEY获取到购物车列表
        Set<String> productKeySet = redisService.getCacheMapKeySet(carKey);
        //根据筛选出已选中的购物车商品信息，生成一个购物车信息列表
        List<CartInfo> cartInfoList = productKeySet.stream()
                .map(productKey ->
                        CartInfo.carProductModelBuild(productKey, redisService.getCacheMapValue(carKey, productKey))
                        //筛选出已选中的购物车商品信息
                ).filter(cartInfo -> "Y".equals(cartInfo.getCheck())).collect(Collectors.toList());
       //通过这个列表获取所有购物车商品的ID。
        List<Long> carInfoIds = cartInfoList.stream().map(CartInfo::getId).collect(Collectors.toList());
        //通过购物车商品的缓存键值，从Redis缓存中删除这些购物车商品信息。
        cartInfoList.stream().map(cartInfo -> KeyUtils.getCarProductKey(cartInfo.getProductId(),cartInfo.getProductSku()))
                .forEach(productKey->redisService.deleteCacheMapValue(carKey,productKey));
        //通过购物车商品的ID，从购物车数据库中删除这些购物车商品信息。
        cartInfoMapper.deleteCartInfoByIds(carInfoIds);
    }


}
