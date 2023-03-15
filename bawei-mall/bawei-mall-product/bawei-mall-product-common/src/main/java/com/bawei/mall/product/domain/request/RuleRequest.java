package com.bawei.mall.product.domain.request;

import com.bawei.mall.product.domain.MallProductRuleInfo;
import com.bawei.mall.product.domain.model.RuleAttrModel;

import java.util.List;

/**
 * @author LuZhixuan
 * @description: 规格请求对象
 * @Date 2022-9-17 上午 09:29
 */

public class RuleRequest extends MallProductRuleInfo {

    private List<RuleAttrModel> ruleList;

    public List<RuleAttrModel> getRuleList () {
        return ruleList;
    }

    public void setRuleList (List<RuleAttrModel> ruleList) {
        this.ruleList = ruleList;
    }
}
