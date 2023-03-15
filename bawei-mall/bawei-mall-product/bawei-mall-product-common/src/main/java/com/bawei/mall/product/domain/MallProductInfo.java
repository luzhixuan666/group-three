package com.bawei.mall.product.domain;

import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.bawei.common.core.annotation.Excel;
import com.bawei.common.core.web.domain.BaseEntity;

/**
 * 商品信息对象 mall_product_info
 *
 * @author Luzhixuan
 * @date 2023-02-30
 */
public class MallProductInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @ApiParam("ID自增")
    private Long id;

    /** 商品名称 */
    @Excel(name = "商品名称")
    @ApiParam("商品名称")
    private String name;

    /** 商品描述 */
    @Excel(name = "商品描述")
    private String productDesc;

    /** 商品类型 */
    @Excel(name = "商品类型")
    private String type;

    /** 冗余字段 */
    @Excel(name = "冗余字段")
    private String typeIds;

    /** 商品主图 */
    @Excel(name = "商品主图")
    private String img;

    /** 商品轮播图 */
    @Excel(name = "商品轮播图")
    private String carouselImages;

    /** 商品评论数 */
    @Excel(name = "商品评论数")
    private Long commentCount;

    /** 商品收藏人气 */
    @Excel(name = "商品收藏人气")
    private Long collectCount;

    /** 品牌信息 */
    @Excel(name = "品牌信息")
    private String brand;

    /** 商品状态 */
    @Excel(name = "商品状态")
    private String status;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 搜索关键字 */
    @Excel(name = "搜索关键字")
    private String keywords;

    /** 规格信息 */
    @Excel(name = "规格信息")
    private Long ruleId;

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
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setTypeIds(String typeIds)
    {
        this.typeIds = typeIds;
    }

    public String getTypeIds()
    {
        return typeIds;
    }
    public void setImg(String img)
    {
        this.img = img;
    }

    public String getImg()
    {
        return img;
    }
    public void setCarouselImages(String carouselImages)
    {
        this.carouselImages = carouselImages;
    }

    public String getCarouselImages()
    {
        return carouselImages;
    }
    public void setCommentCount(Long commentCount)
    {
        this.commentCount = commentCount;
    }

    public Long getCommentCount()
    {
        return commentCount;
    }
    public void setCollectCount(Long collectCount)
    {
        this.collectCount = collectCount;
    }

    public Long getCollectCount()
    {
        return collectCount;
    }
    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public String getBrand()
    {
        return brand;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getUnit()
    {
        return unit;
    }
    public void setKeywords(String keywords)
    {
        this.keywords = keywords;
    }

    public String getKeywords()
    {
        return keywords;
    }
    public void setRuleId(Long ruleId)
    {
        this.ruleId = ruleId;
    }

    public Long getRuleId()
    {
        return ruleId;
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
            .append("type", getType())
            .append("typeIds", getTypeIds())
            .append("img", getImg())
            .append("carouselImages", getCarouselImages())
            .append("commentCount", getCommentCount())
            .append("collectCount", getCollectCount())
            .append("brand", getBrand())
            .append("status", getStatus())
            .append("unit", getUnit())
            .append("keywords", getKeywords())
            .append("ruleId", getRuleId())
            .append("revision", getRevision())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
