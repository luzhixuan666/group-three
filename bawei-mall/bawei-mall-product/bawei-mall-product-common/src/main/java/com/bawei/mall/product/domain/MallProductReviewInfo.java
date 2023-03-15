package com.bawei.mall.product.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.bawei.common.core.annotation.Excel;
import com.bawei.common.core.web.domain.BaseEntity;

/**
 * 商品评价对象 mall_product_review_info
 *
 * @author Luzhixuan
 * @date 2022-09-26
 */
public class MallProductReviewInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private Long productId;

    /** 商品SKU */
    @Excel(name = "商品SKU")
    private Long productSkuId;

    /** 商品评价图片 */
    @Excel(name = "商品评价图片")
    private String reviewImages;

    /** 商品评价信息 */
    @Excel(name = "商品评价信息")
    private String content;

    /** 评论分数 */
    @Excel(name = "评论分数")
    private BigDecimal start;

    /** 是否隐藏 */
    @Excel(name = "是否隐藏")
    private String isDispaly;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private String isDel;

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
    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getProductId()
    {
        return productId;
    }
    public void setProductSkuId(Long productSkuId)
    {
        this.productSkuId = productSkuId;
    }

    public Long getProductSkuId()
    {
        return productSkuId;
    }
    public void setReviewImages(String reviewImages)
    {
        this.reviewImages = reviewImages;
    }

    public String getReviewImages()
    {
        return reviewImages;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setStart(BigDecimal start)
    {
        this.start = start;
    }

    public BigDecimal getStart()
    {
        return start;
    }
    public void setIsDispaly(String isDispaly)
    {
        this.isDispaly = isDispaly;
    }

    public String getIsDispaly()
    {
        return isDispaly;
    }
    public void setIsDel(String isDel)
    {
        this.isDel = isDel;
    }

    public String getIsDel()
    {
        return isDel;
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
            .append("productId", getProductId())
            .append("productSkuId", getProductSkuId())
            .append("reviewImages", getReviewImages())
            .append("content", getContent())
            .append("start", getStart())
            .append("isDispaly", getIsDispaly())
            .append("isDel", getIsDel())
            .append("revision", getRevision())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
