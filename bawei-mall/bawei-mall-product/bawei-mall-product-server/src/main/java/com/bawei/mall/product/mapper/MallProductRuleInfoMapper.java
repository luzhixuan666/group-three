package com.bawei.mall.product.mapper;

import java.util.List;
import com.bawei.mall.product.domain.MallProductRuleInfo;

/**
 * 商品规格Mapper接口
 *
 * @author Luzhixuan
 * @date 2023-02-30
 */
public interface MallProductRuleInfoMapper
{
    /**
     * 查询商品规格
     *
     * @param id 商品规格主键
     * @return 商品规格
     */
    public MallProductRuleInfo selectMallProductRuleInfoById(Long id);

    /**
     * 查询商品规格列表
     *
     * @param mallProductRuleInfo 商品规格
     * @return 商品规格集合
     */
    public List<MallProductRuleInfo> selectMallProductRuleInfoList(MallProductRuleInfo mallProductRuleInfo);

    /**
     * 新增商品规格
     *
     * @param mallProductRuleInfo 商品规格
     * @return 结果
     */
    public int insertMallProductRuleInfo(MallProductRuleInfo mallProductRuleInfo);

    /**
     * 修改商品规格
     *
     * @param mallProductRuleInfo 商品规格
     * @return 结果
     */
    public int updateMallProductRuleInfo(MallProductRuleInfo mallProductRuleInfo);

    /**
     * 删除商品规格
     *
     * @param id 商品规格主键
     * @return 结果
     */
    public int deleteMallProductRuleInfoById(Long id);

    /**
     * 批量删除商品规格
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMallProductRuleInfoByIds(Long[] ids);
}
