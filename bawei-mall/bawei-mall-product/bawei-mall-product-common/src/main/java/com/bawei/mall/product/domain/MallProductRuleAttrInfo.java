package com.bawei.mall.product.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.bawei.common.core.annotation.Excel;
import com.bawei.common.core.web.domain.BaseEntity;

/**
 * 商品规格详情对象 mall_product_rule_attr_info
 *
 * @author Luzhixuan
 * @date 2023-02-30
 */
public class MallProductRuleAttrInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 规格 */
    @Excel(name = "规格")
    private Long ruleId;

    /** 类目名称 */
    @Excel(name = "类目名称")
    private String name;

    /** 规格值 */
    @Excel(name = "规格值")
    private String attrValue;

    /** 乐观锁 */
    private Long revision;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setRuleId(Long ruleId)
    {
        this.ruleId = ruleId;
    }

    public Long getRuleId()
    {
        return ruleId;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setAttrValue(String attrValue)
    {
        this.attrValue = attrValue;
    }

    public String getAttrValue()
    {
        return attrValue;
    }
    public void setRevision(Long revision)
    {
        this.revision = revision;
    }

    public Long getRevision()
    {
        return revision;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ruleId", getRuleId())
            .append("name", getName())
            .append("attrValue", getAttrValue())
            .append("revision", getRevision())
            .toString();
    }
}
