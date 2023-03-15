package com.bawei.mall.product.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.bawei.common.core.annotation.Excel;
import com.bawei.common.core.web.domain.BaseEntity;

/**
 * 商品品牌对象 mall_product_brand_info
 *
 * @author Luzhixuan
 * @date 2022-09-15
 */
public class MallProductBrandInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 品牌名称 */
    @Excel(name = "品牌名称")
    private String name;

    /** 品牌描述 */
    @Excel(name = "品牌描述")
    private String productDesc;

    /** 品牌介绍 */
    private String content;

    /** 品牌logo */
    @Excel(name = "品牌logo")
    private String logo;

    /** 品牌状态 */
    @Excel(name = "品牌状态")
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
    public void setProductDesc(String productDesc)
    {
        this.productDesc = productDesc;
    }

    public String getProductDesc()
    {
        return productDesc;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setLogo(String logo)
    {
        this.logo = logo;
    }

    public String getLogo()
    {
        return logo;
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
            .append("productDesc", getProductDesc())
            .append("content", getContent())
            .append("logo", getLogo())
            .append("status", getStatus())
            .append("revision", getRevision())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
