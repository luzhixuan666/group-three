package com.bawei.mall.product.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.bawei.common.core.annotation.Excel;
import com.bawei.common.core.web.domain.BaseEntity;

/**
 * 商品规格对象 mall_product_rule_info
 *
 * @author Luzhixuan
 * @date 2023-02-30
 */
public class MallProductRuleInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 规格名称 */
    @Excel(name = "规格名称")
    private String name;

    /** 规格详情 */
    @Excel(name = "规格详情")
    private String ruleAttr;

    /** 规格状态 */
    @Excel(name = "规格状态")
    private String status;

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
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setRuleAttr(String ruleAttr)
    {
        this.ruleAttr = ruleAttr;
    }

    public String getRuleAttr()
    {
        return ruleAttr;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
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
            .append("name", getName())
            .append("ruleAttr", getRuleAttr())
            .append("status", getStatus())
            .append("revision", getRevision())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
