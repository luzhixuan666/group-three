package com.bawei.mall.product.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LuZhixuan
 * @description: 规格类型详情模型
 * @Date 2022-9-17 上午 09:28
 */
public class RuleAttrModel {

    private String ruleType;

    private List<String> ruleAttrList;

    private String ruleAttrStr;

    public String getRuleType () {
        return ruleType;
    }

    public void setRuleType (String ruleType) {
        this.ruleType = ruleType;
    }

    public List<String> getRuleAttrList () {
        return ruleAttrList;
    }

    public void setRuleAttrList (List<String> ruleAttrList) {
        this.ruleAttrList = ruleAttrList;
        if (this.ruleAttrList != null){
            this.ruleAttrStr = this.ruleAttrList.stream().collect(Collectors.joining(","));
        }
    }

    public String getRuleAttrStr () {
        return ruleAttrStr;
    }

}
