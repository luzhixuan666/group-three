package com.bawei.mall.product.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.bawei.common.core.annotation.Excel;
import com.bawei.common.core.web.domain.BaseEntity;

/**
 * 商品SKU对象 mall_product_sku_info
 *
 * @author Luzhixuan
 * @date 2023-02-30
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MallProductSkuInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 商品信息 */
    @Excel(name = "商品信息")
    private Long productId;

    /** 商品规格 */
    @Excel(name = "商品规格")
    private String sku;

    /** 商品库存 */
    @Excel(name = "商品库存")
    private Long stock;

    /** 商品价格 */
    @Excel(name = "商品价格")
    private BigDecimal price;

    /** 商品进价 */
    @Excel(name = "商品进价")
    private BigDecimal purchasePrice;

    /** 商品售价 */
    @Excel(name = "商品售价")
    private BigDecimal sellingPrice;

    /** 规格图片 */
    @Excel(name = "规格图片")
    private String image;

    /** 编号 */
    @Excel(name = "编号")
    private String number;

    /** 重量 */
    @Excel(name = "重量")
    private BigDecimal weight;

    /** 体积 */
    @Excel(name = "体积")
    private BigDecimal volume;

    /** 乐观锁 */
    @Excel(name = "乐观锁")
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
    public void setSku(String sku)
    {
        this.sku = sku;
    }

    public String getSku()
    {
        return sku;
    }
    public void setStock(Long stock)
    {
        this.stock = stock;
    }

    public Long getStock()
    {
        return stock;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
    public void setPurchasePrice(BigDecimal purchasePrice)
    {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getPurchasePrice()
    {
        return purchasePrice;
    }
    public void setSellingPrice(BigDecimal sellingPrice)
    {
        this.sellingPrice = sellingPrice;
    }

    public BigDecimal getSellingPrice()
    {
        return sellingPrice;
    }
    public void setImage(String image)
    {
        this.image = image;
    }

    public String getImage()
    {
        return image;
    }
    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getNumber()
    {
        return number;
    }
    public void setWeight(BigDecimal weight)
    {
        this.weight = weight;
    }

    public BigDecimal getWeight()
    {
        return weight;
    }
    public void setVolume(BigDecimal volume)
    {
        this.volume = volume;
    }

    public BigDecimal getVolume()
    {
        return volume;
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
            .append("sku", getSku())
            .append("stock", getStock())
            .append("price", getPrice())
            .append("purchasePrice", getPurchasePrice())
            .append("sellingPrice", getSellingPrice())
            .append("image", getImage())
            .append("number", getNumber())
            .append("weight", getWeight())
            .append("volume", getVolume())
            .append("revision", getRevision())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
