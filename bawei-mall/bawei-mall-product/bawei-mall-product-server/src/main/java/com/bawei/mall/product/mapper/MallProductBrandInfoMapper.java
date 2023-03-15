package com.bawei.mall.product.mapper;

import java.util.List;
import com.bawei.mall.product.domain.MallProductBrandInfo;

/**
 * 商品品牌Mapper接口
 *
 * @author Luzhixuan
 * @date 2022-09-15
 */
public interface MallProductBrandInfoMapper
{
    /**
     * 查询商品品牌
     *
     * @param id 商品品牌主键
     * @return 商品品牌
     */
    public MallProductBrandInfo selectMallProductBrandInfoById(Long id);

    /**
     * 查询商品品牌列表
     *
     * @param mallProductBrandInfo 商品品牌
     * @return 商品品牌集合
     */
    public List<MallProductBrandInfo> selectMallProductBrandInfoList(MallProductBrandInfo mallProductBrandInfo);

    /**
     * 新增商品品牌
     *
     * @param mallProductBrandInfo 商品品牌
     * @return 结果
     */
    public int insertMallProductBrandInfo(MallProductBrandInfo mallProductBrandInfo);

    /**
     * 修改商品品牌
     *
     * @param mallProductBrandInfo 商品品牌
     * @return 结果
     */
    public int updateMallProductBrandInfo(MallProductBrandInfo mallProductBrandInfo);

    /**
     * 删除商品品牌
     *
     * @param id 商品品牌主键
     * @return 结果
     */
    public int deleteMallProductBrandInfoById(Long id);

    /**
     * 批量删除商品品牌
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMallProductBrandInfoByIds(Long[] ids);
}
