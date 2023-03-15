package com.bawei.mall.product.domain.model;

import java.util.List;

/**
 * @author LuZhixuan
 * @description: 规格模型
 * @Date 2022-9-17 上午 09:33
 */
public class RuleModel {

    private Long ruleId;

    private List<RuleAttrModel> ruleList;

    public Long getRuleId () {
        return ruleId;
    }

    public void setRuleId (Long ruleId) {
        this.ruleId = ruleId;
    }

    public List<RuleAttrModel> getRuleList () {
        return ruleList;
    }

    public void setRuleList (List<RuleAttrModel> ruleList) {
        this.ruleList = ruleList;
    }
}
