package com.bawei.mall.product.service;

import java.util.List;
import com.bawei.mall.product.domain.MallProductSkuInfo;
import com.bawei.mall.product.domain.model.SkuModel;

/**
 * 商品SKUService接口
 *
 * @author Luzhixuan
 * @date 2023-02-30
 */
public interface IMallProductSkuInfoService
{
    /**
     * 查询商品SKU
     *
     * @param id 商品SKU主键
     * @return 商品SKU
     */
    public MallProductSkuInfo selectMallProductSkuInfoById(Long id);

    /**
     * 查询商品SKU列表
     *
     * @param mallProductSkuInfo 商品SKU
     * @return 商品SKU集合
     */
    public List<MallProductSkuInfo> selectMallProductSkuInfoList(MallProductSkuInfo mallProductSkuInfo);
    /**
     * 通过商品Id查询商品SKU列表
     *
     * @param productId 商品Id
     * @return 商品SKU集合
     */
    public default List<MallProductSkuInfo> selectMallProductSkuInfoList(Long productId){
        return this.selectMallProductSkuInfoList(new MallProductSkuInfo(){{
            setProductId(productId);
        }});
    }



    /**
     * 新增商品SKU
     *
     * @param mallProductSkuInfo 商品SKU
     * @return 结果
     */
    public int insertMallProductSkuInfo(MallProductSkuInfo mallProductSkuInfo);
    public int batchInsertProductSku(SkuModel skuModel);

    /**
     * 修改商品SKU
     *
     * @param mallProductSkuInfo 商品SKU
     * @return 结果
     */
    public int updateMallProductSkuInfo(MallProductSkuInfo mallProductSkuInfo);

    /**
     * 批量删除商品SKU
     *
     * @param ids 需要删除的商品SKU主键集合
     * @return 结果
     */
    public int deleteMallProductSkuInfoByIds(Long[] ids);

    /**
     * 删除商品SKU信息
     *
     * @param id 商品SKU主键
     * @return 结果
     */
    public int deleteMallProductSkuInfoById(Long id);

    /**
     * 删除商品的sku
     * @param productId
     */
    void deleteMallProductSkuInfoByProductId (Long productId);

    /**
     * 通过商品信息的Ids进行删除
     * @param ids
     */
    void deleteMallProductSkuInfoByProductIds (Long[] ids);


    /**
     * 扣减库存
     * @param productSkuInfo
     */
    void deductionStock(MallProductSkuInfo productSkuInfo);
}

