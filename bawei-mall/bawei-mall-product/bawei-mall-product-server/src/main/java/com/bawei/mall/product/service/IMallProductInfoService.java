package com.bawei.mall.product.service;

import java.util.List;
import com.bawei.mall.product.domain.MallProductInfo;
import com.bawei.mall.product.domain.reponse.ProductDetailsResponse;
import com.bawei.mall.product.domain.reponse.ProductInfoResponse;
import com.bawei.mall.product.domain.request.ProductInfoRequest;

/**
 * 商品信息Service接口
 *
 * @author Luzhixuan
 * @date 2023-02-30
 */
public interface IMallProductInfoService
{
    /**
     * 查询商品信息
     *
     * @param id 商品信息主键
     * @return 商品信息
     */
    public ProductInfoResponse selectMallProductInfoById(Long id);
    public ProductDetailsResponse selectProductDetailsById(Long id);

    /**
     * 查询商品信息列表
     *
     * @param mallProductInfo 商品信息
     * @return 商品信息集合
     */
    public List<MallProductInfo> selectMallProductInfoList(MallProductInfo mallProductInfo);

    /**
     * 新增商品信息
     *
     * @param productInfoRequest 商品信息
     * @return 结果
     */
    public int insertMallProductInfo(ProductInfoRequest productInfoRequest);

    /**
     * 修改商品信息
     *
     * @param productInfoRequest 商品信息
     * @return 结果
     */
    public int updateMallProductInfo(ProductInfoRequest productInfoRequest);

    /**
     * 批量删除商品信息
     *
     * @param ids 需要删除的商品信息主键集合
     * @return 结果
     */
    public int deleteMallProductInfoByIds(Long[] ids);

    /**
     * 删除商品信息信息
     *
     * @param id 商品信息主键
     * @return 结果
     */
    public int deleteMallProductInfoById(Long id);

    /**
     * 查询商品总条数
     * @param mallProductInfo 商品查询
     * @return
     */
    Long selectMallProductInfoCount (MallProductInfo mallProductInfo);

    /**
     * 锁定库存
     */
    void lockProductStock();

}
