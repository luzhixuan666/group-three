package com.bawei.mall.product.mapper;

import java.util.List;
import com.bawei.mall.product.domain.MallProductRuleAttrInfo;
import com.bawei.mall.product.domain.model.RuleModel;
import org.apache.ibatis.annotations.Param;

/**
 * 商品规格详情Mapper接口
 *
 * @author Luzhixuan
 * @date 2023-02-30
 */
public interface MallProductRuleAttrInfoMapper
{
    /**
     * 查询商品规格详情
     *
     * @param id 商品规格详情主键
     * @return 商品规格详情
     */
    public MallProductRuleAttrInfo selectMallProductRuleAttrInfoById(Long id);

    /**
     * 查询商品规格详情列表
     *
     * @param mallProductRuleAttrInfo 商品规格详情
     * @return 商品规格详情集合
     */
    public List<MallProductRuleAttrInfo> selectMallProductRuleAttrInfoList(MallProductRuleAttrInfo mallProductRuleAttrInfo);

    /**
     * 新增商品规格详情
     *
     * @param mallProductRuleAttrInfo 商品规格详情
     * @return 结果
     */
    public int insertMallProductRuleAttrInfo(MallProductRuleAttrInfo mallProductRuleAttrInfo);

    /**
     * 修改商品规格详情
     *
     * @param mallProductRuleAttrInfo 商品规格详情
     * @return 结果
     */
    public int updateMallProductRuleAttrInfo(MallProductRuleAttrInfo mallProductRuleAttrInfo);

    /**
     * 删除商品规格详情
     *
     * @param id 商品规格详情主键
     * @return 结果
     */
    public int deleteMallProductRuleAttrInfoById(Long id);

    /**
     * 批量删除商品规格详情
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMallProductRuleAttrInfoByIds(Long[] ids);

    /**
     * 批量添加
     * @param ruleModel
     * @return
     */
    int bacthInsertRule (@Param("ruleModel") RuleModel ruleModel);

    /**
     * 删除规格详情
     * @param ids 规格Ids
     * @return
     */
    int deleteRuleAttrByRuleIds (Long[] ids);
}
