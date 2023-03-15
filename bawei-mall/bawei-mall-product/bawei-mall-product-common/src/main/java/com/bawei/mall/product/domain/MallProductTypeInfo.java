package com.bawei.mall.product.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.bawei.common.core.annotation.Excel;
import com.bawei.common.core.web.domain.TreeEntity;

/**
 * 商品类型对象 mall_product_type_info
 *
 * @author Luzhixuan
 * @date 2023-02-30
 */
public class MallProductTypeInfo extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String name;

    /** 类型图片 */
    @Excel(name = "类型图片")
    private String image;

    /** 类型状态 */
    @Excel(name = "类型状态")
    private String status;

    /** 类型排序 */
    @Excel(name = "类型排序")
    private Long orderBy;

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
    public void setImage(String image)
    {
        this.image = image;
    }

    public String getImage()
    {
        return image;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setOrderBy(Long orderBy)
    {
        this.orderBy = orderBy;
    }

    public Long getOrderBy()
    {
        return orderBy;
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
            .append("image", getImage())
            .append("status", getStatus())
            .append("orderBy", getOrderBy())
            .append("parentId", getParentId())
            .append("revision", getRevision())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
