package com.bawei.mall.cart.service;

import java.util.List;
import com.bawei.mall.cart.domain.CartInfo;
import com.bawei.mall.cart.domain.model.*;
import com.bawei.mall.cart.domain.request.RemoveCarProductRequest;
import com.bawei.mall.cart.domain.respone.CarInfoRespone;

/**
 * 购物车Service接口
 *
 * @author Luzhixuan
 * @date 2023-01-30
 */
public interface ICartInfoService
{
    /**
     * 查询购物车
     *
     * @param id 购物车主键
     * @return 购物车
     */
    public CartInfo selectCartInfoById(Long id);

    /**
     * 查询购物车列表
     *
     * @param cartInfo 购物车
     * @return 购物车集合
     */
    public List<CartInfo> selectCartInfoList(CartInfo cartInfo);

    /**
     * 新增购物车
     *
     * @param cartInfo 购物车
     * @return 结果
     */
    public int insertCartInfo(CartInfo cartInfo);

    /**
     * 修改购物车
     *
     * @param cartInfo 购物车
     * @return 结果
     */
    public int updateCartInfo(CartInfo cartInfo);

    /**
     * 批量删除购物车
     *
     * @param
     * @return 结果
     */
    public int deleteCartInfo(List<RemoveCarProductModel> removeCarProductModelList);

    /**
     * 删除购物车信息
     *
     * @param id 购物车主键
     * @return 结果
     */
    public int deleteCartInfoById(Long id);



    public void addCar(AddCarModel addCarModel);

    /**
     * 查看购物车所有信息
     */
    CarInfoRespone cartInfoAllList();


    /**
     * 是否选择
     * @param carCheckProductModel
     */
    void carCheckProduct(CarCheckProductModel carCheckProductModel);

    /**
     * 商品快捷选择sku
     * @param checkSkuModel
     */
    void checkSku(CheckSkuModel checkSkuModel);


    /**
     * 购物车商品数量
     */
    void updateSkuCount(UpdateSkuCountModel updateSkuCountModel);

    /**
     * 清空购物车内选中的
     */
    void clearCarChangeProduct();
}
