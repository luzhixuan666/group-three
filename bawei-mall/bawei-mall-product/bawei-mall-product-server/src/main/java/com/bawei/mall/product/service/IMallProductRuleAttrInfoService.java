package com.bawei.mall.product.service;

import java.util.List;
import com.bawei.mall.product.domain.MallProductRuleAttrInfo;
import com.bawei.mall.product.domain.model.RuleAttrModel;
import com.bawei.mall.product.domain.model.RuleModel;

/**
 * 商品规格详情Service接口
 *
 * @author Luzhixuan
 * @date 2023-02-30
 */
public interface IMallProductRuleAttrInfoService
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
     * 新增商品规格详情
     *
     * @param ruleModel 商品规格详情
     * @return 结果
     */
    public int insertRuleModel(RuleModel ruleModel);
    default int insertRuleModel (Long id, List<RuleAttrModel> ruleList){
        return this.insertRuleModel(new RuleModel(){{
            setRuleId(id);
            setRuleList(ruleList);
        }});
    }

    /**
     * 修改商品规格详情
     *
     * @param mallProductRuleAttrInfo 商品规格详情
     * @return 结果
     */
    public int updateMallProductRuleAttrInfo(MallProductRuleAttrInfo mallProductRuleAttrInfo);

    /**
     * 批量删除商品规格详情
     *
     * @param ids 需要删除的商品规格详情主键集合
     * @return 结果
     */
    public int deleteMallProductRuleAttrInfoByIds(Long[] ids);

    /**
     * 删除商品规格详情信息
     *
     * @param id 商品规格详情主键
     * @return 结果
     */
    public int deleteMallProductRuleAttrInfoById(Long id);

    /**
     * 删除商品规格详情信息
     * @param ids 规格Ids
     * @return
     */
    public int deleteRuleAttrByRuleIds(Long[] ids);

    /**
     * 删除商品规格详情
     * @param id 规格ID
     */
    default int deleteRuleAttrByRuleId (Long id){
        return this.deleteRuleAttrByRuleIds(new Long[]{id});
    }

}
