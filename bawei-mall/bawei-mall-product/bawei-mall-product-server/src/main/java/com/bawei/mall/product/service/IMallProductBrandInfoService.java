package com.bawei.mall.product.service;

import java.util.List;
import com.bawei.mall.product.domain.MallProductBrandInfo;

/**
 * 商品品牌Service接口
 *
 * @author Luzhixuan
 * @date 2022-09-15
 */
public interface IMallProductBrandInfoService
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
     * 批量删除商品品牌
     *
     * @param ids 需要删除的商品品牌主键集合
     * @return 结果
     */
    public int deleteMallProductBrandInfoByIds(Long[] ids);

    /**
     * 删除商品品牌信息
     *
     * @param id 商品品牌主键
     * @return 结果
     */
    public int deleteMallProductBrandInfoById(Long id);
}
