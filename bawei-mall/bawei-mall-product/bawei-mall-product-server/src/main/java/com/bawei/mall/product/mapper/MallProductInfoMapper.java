package com.bawei.mall.product.mapper;

import java.util.List;
import com.bawei.mall.product.domain.MallProductInfo;
import com.bawei.mall.product.domain.MallProductSkuInfo;
import com.bawei.mall.product.domain.model.ProductModel;
import org.apache.ibatis.annotations.Param;

/**
 * 商品信息Mapper接口
 *
 * @author Luzhixuan
 * @date 2023-02-30
 */
public interface MallProductInfoMapper
{
    /**
     * 查询商品信息
     *
     * @param id 商品信息主键
     * @return 商品信息
     */
    public MallProductInfo selectMallProductInfoById(@Param("id")Long id);

    public ProductModel selectProductModelById(@Param("id") Long id);

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
     * @param mallProductInfo 商品信息
     * @return 结果
     */
    public int insertMallProductInfo(MallProductInfo mallProductInfo);

    /**
     * 修改商品信息
     *
     * @param mallProductInfo 商品信息
     * @return 结果
     */
    public int updateMallProductInfo(MallProductInfo mallProductInfo);

    /**
     * 删除商品信息
     *
     * @param id 商品信息主键
     * @return 结果
     */
    public int deleteMallProductInfoById(Long id);

    /**
     * 批量删除商品信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMallProductInfoByIds(Long[] ids);

    /**
     * 商品总条数
     * @param mallProductInfo 商品查询条件
     * @return
     */
    Long selectMallProductInfoCount (MallProductInfo mallProductInfo);


    List<MallProductInfo> findAll();

}
