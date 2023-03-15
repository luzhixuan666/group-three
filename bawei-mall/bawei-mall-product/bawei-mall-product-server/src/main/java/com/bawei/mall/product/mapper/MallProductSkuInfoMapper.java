package com.bawei.mall.product.mapper;

import java.util.List;
import com.bawei.mall.product.domain.MallProductSkuInfo;
import com.bawei.mall.product.domain.model.SkuModel;
import org.apache.ibatis.annotations.Param;

/**
 * 商品SKUMapper接口
 *
 * @author Luzhixuan
 * @date 2023-02-30
 */
public interface MallProductSkuInfoMapper
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
     * 新增商品SKU
     *
     * @param mallProductSkuInfo 商品SKU
     * @return 结果
     */
    public int insertMallProductSkuInfo(MallProductSkuInfo mallProductSkuInfo);

    /**
     * 修改商品SKU
     *
     * @param mallProductSkuInfo 商品SKU
     * @return 结果
     */
    public int updateMallProductSkuInfo(MallProductSkuInfo mallProductSkuInfo);

    /**
     * 删除商品SKU
     *
     * @param id 商品SKU主键
     * @return 结果
     */
    public int deleteMallProductSkuInfoById(Long id);

    /**
     * 批量删除商品SKU
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMallProductSkuInfoByIds(Long[] ids);

    /**
     * 批量添加
     * @param skuModel
     * @return
     */
    int batchInsertProductSku (SkuModel skuModel);

    /**
     * 删除商品下的SKU
     * @param productId
     */
    void deleteMallProductSkuInfoByProductId (@Param("productId") Long productId);

    /**
     * 通过商品信息的ids进行删除
     * @param ids
     */
    void deleteMallProductSkuInfoByProductIds (Long[] ids);

    /**
     * 扣减库存
     * @param productSkuInfo
     */
    void deductionStock(MallProductSkuInfo productSkuInfo);

}
