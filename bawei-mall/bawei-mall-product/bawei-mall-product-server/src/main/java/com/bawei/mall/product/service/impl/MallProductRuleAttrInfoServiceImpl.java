package com.bawei.mall.product.service.impl;

import java.util.List;

import com.bawei.mall.product.domain.model.RuleAttrModel;
import com.bawei.mall.product.domain.model.RuleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bawei.mall.product.mapper.MallProductRuleAttrInfoMapper;
import com.bawei.mall.product.domain.MallProductRuleAttrInfo;
import com.bawei.mall.product.service.IMallProductRuleAttrInfoService;

/**
 * 商品规格详情Service业务层处理
 *
 * @author Luzhixuan
 * @date 2023-02-30
 */
@Service
public class MallProductRuleAttrInfoServiceImpl implements IMallProductRuleAttrInfoService
{
    @Autowired
    private MallProductRuleAttrInfoMapper mallProductRuleAttrInfoMapper;

    /**
     * 查询商品规格详情
     *
     * @param id 商品规格详情主键
     * @return 商品规格详情
     */
    @Override
    public MallProductRuleAttrInfo selectMallProductRuleAttrInfoById(Long id)
    {
        return mallProductRuleAttrInfoMapper.selectMallProductRuleAttrInfoById(id);
    }

    /**
     * 查询商品规格详情列表
     *
     * @param mallProductRuleAttrInfo 商品规格详情
     * @return 商品规格详情
     */
    @Override
    public List<MallProductRuleAttrInfo> selectMallProductRuleAttrInfoList(MallProductRuleAttrInfo mallProductRuleAttrInfo)
    {
        return mallProductRuleAttrInfoMapper.selectMallProductRuleAttrInfoList(mallProductRuleAttrInfo);
    }

    /**
     * 新增商品规格详情
     *
     * @param mallProductRuleAttrInfo 商品规格详情
     * @return 结果
     */
    @Override
    public int insertMallProductRuleAttrInfo(MallProductRuleAttrInfo mallProductRuleAttrInfo)
    {
        return mallProductRuleAttrInfoMapper.insertMallProductRuleAttrInfo(mallProductRuleAttrInfo);
    }

    @Override
    public int insertRuleModel (RuleModel ruleModel) {

        return mallProductRuleAttrInfoMapper.bacthInsertRule(ruleModel);
    }

    /**
     * 修改商品规格详情
     *
     * @param mallProductRuleAttrInfo 商品规格详情
     * @return 结果
     */
    @Override
    public int updateMallProductRuleAttrInfo(MallProductRuleAttrInfo mallProductRuleAttrInfo)
    {
        return mallProductRuleAttrInfoMapper.updateMallProductRuleAttrInfo(mallProductRuleAttrInfo);
    }

    /**
     * 批量删除商品规格详情
     *
     * @param ids 需要删除的商品规格详情主键
     * @return 结果
     */
    @Override
    public int deleteMallProductRuleAttrInfoByIds(Long[] ids)
    {
        return mallProductRuleAttrInfoMapper.deleteMallProductRuleAttrInfoByIds(ids);
    }

    /**
     * 删除商品规格详情信息
     *
     * @param id 商品规格详情主键
     * @return 结果
     */
    @Override
    public int deleteMallProductRuleAttrInfoById(Long id)
    {
        return mallProductRuleAttrInfoMapper.deleteMallProductRuleAttrInfoById(id);
    }

    /**
     * 删除商品规格详情
     * @param ids 规格Ids
     * @return
     */
    @Override
    public int deleteRuleAttrByRuleIds (Long[] ids) {
        return mallProductRuleAttrInfoMapper.deleteRuleAttrByRuleIds(ids);
    }
}
