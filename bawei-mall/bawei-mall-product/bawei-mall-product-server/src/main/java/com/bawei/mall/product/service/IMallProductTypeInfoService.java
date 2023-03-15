package com.bawei.mall.product.service;

import java.util.List;
import com.bawei.mall.product.domain.MallProductTypeInfo;

/**
 * 商品类型Service接口
 *
 * @author Luzhixuan
 * @date 2023-02-30
 */
public interface IMallProductTypeInfoService
{
    /**
     * 查询商品类型
     *
     * @param id 商品类型主键
     * @return 商品类型
     */
    public MallProductTypeInfo selectMallProductTypeInfoById(Long id);

    /**
     * 查询商品类型列表
     *
     * @param mallProductTypeInfo 商品类型
     * @return 商品类型集合
     */
    public List<MallProductTypeInfo> selectMallProductTypeInfoList(MallProductTypeInfo mallProductTypeInfo);

    /**
     * 新增商品类型
     *
     * @param mallProductTypeInfo 商品类型
     * @return 结果
     */
    public int insertMallProductTypeInfo(MallProductTypeInfo mallProductTypeInfo);

    /**
     * 修改商品类型
     *
     * @param mallProductTypeInfo 商品类型
     * @return 结果
     */
    public int updateMallProductTypeInfo(MallProductTypeInfo mallProductTypeInfo);

    /**
     * 批量删除商品类型
     *
     * @param ids 需要删除的商品类型主键集合
     * @return 结果
     */
    public int deleteMallProductTypeInfoByIds(Long[] ids);

    /**
     * 删除商品类型信息
     *
     * @param id 商品类型主键
     * @return 结果
     */
    public int deleteMallProductTypeInfoById(Long id);
}
