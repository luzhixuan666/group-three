package com.bawei.mall.product.service.impl;

import java.util.List;

import com.bawei.common.core.utils.DateUtils;
import com.bawei.common.security.utils.SecurityUtils;
import com.bawei.mall.product.domain.model.RuleModel;
import com.bawei.mall.product.domain.request.RuleRequest;
import com.bawei.mall.product.service.IMallProductRuleAttrInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bawei.mall.product.mapper.MallProductRuleInfoMapper;
import com.bawei.mall.product.domain.MallProductRuleInfo;
import com.bawei.mall.product.service.IMallProductRuleInfoService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品规格Service业务层处理
 *
 * @author Luzhixuan
 * @date 2023-02-30
 */
@Service
public class MallProductRuleInfoServiceImpl implements IMallProductRuleInfoService
{
    @Autowired
    private MallProductRuleInfoMapper mallProductRuleInfoMapper;

    @Autowired
    private IMallProductRuleAttrInfoService ruleAttrInfoService;

    /**
     * 查询商品规格
     *
     * @param id 商品规格主键
     * @return 商品规格
     */
    @Override
    public MallProductRuleInfo selectMallProductRuleInfoById(Long id)
    {
        return mallProductRuleInfoMapper.selectMallProductRuleInfoById(id);
    }

    /**
     * 查询商品规格列表
     *
     * @param mallProductRuleInfo 商品规格
     * @return 商品规格
     */
    @Override
    public List<MallProductRuleInfo> selectMallProductRuleInfoList(MallProductRuleInfo mallProductRuleInfo)
    {
        return mallProductRuleInfoMapper.selectMallProductRuleInfoList(mallProductRuleInfo);
    }

    /**
     * 新增商品规格
     *
     * @param ruleRequest 商品规格
     * @return 结果
     */
    @Override
    @Transactional
    public int insertMallProductRuleInfo(RuleRequest ruleRequest)
    {
        ruleRequest.setCreateBy(String.valueOf(SecurityUtils.getUserId()));
        ruleRequest.setCreateTime(DateUtils.getNowDate());
        int i = mallProductRuleInfoMapper.insertMallProductRuleInfo(ruleRequest);
        if (i == 0){
            return i;
        }
        // 组装了一个Model 用来添加规格属性
        RuleModel ruleModel = new RuleModel();
        ruleModel.setRuleId(ruleRequest.getId());
        ruleModel.setRuleList(ruleRequest.getRuleList());
        ruleAttrInfoService.insertRuleModel(ruleModel);
        return i;

    }

    /**
     * 修改商品规格
     *
     * @param ruleRequest 商品规格
     * @return 结果
     */
    @Override
    public int updateMallProductRuleInfo(RuleRequest ruleRequest)
    {
        ruleRequest.setUpdateBy(String.valueOf(SecurityUtils.getUserId()));
        ruleRequest.setUpdateTime(DateUtils.getNowDate());
        int i = mallProductRuleInfoMapper.updateMallProductRuleInfo(ruleRequest);
        if (i == 0){
            return i;
        }
        ruleAttrInfoService.deleteRuleAttrByRuleId(ruleRequest.getId());
        ruleAttrInfoService.insertRuleModel(ruleRequest.getId(), ruleRequest.getRuleList());
        return i;
    }

    /**
     * 批量删除商品规格
     *
     * @param ids 需要删除的商品规格主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteMallProductRuleInfoByIds(Long[] ids)
    {
        ruleAttrInfoService.deleteRuleAttrByRuleIds(ids);
        return mallProductRuleInfoMapper.deleteMallProductRuleInfoByIds(ids);
    }

    /**
     * 删除商品规格信息
     *
     * @param id 商品规格主键
     * @return 结果
     */
    @Override
    public int deleteMallProductRuleInfoById(Long id)
    {
        return mallProductRuleInfoMapper.deleteMallProductRuleInfoById(id);
    }
}
