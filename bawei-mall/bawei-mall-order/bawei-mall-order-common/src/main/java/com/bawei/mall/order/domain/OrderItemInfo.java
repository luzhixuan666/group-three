package com.bawei.mall.order.domain;

import java.math.BigDecimal;

import com.bawei.mall.product.domain.model.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.bawei.common.core.annotation.Excel;
import com.bawei.common.core.web.domain.BaseEntity;

/**
 * 订单明细对象 order_item_info
 *
 * @author Luzhixuan
 * @date 2023-02-09
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderNumber;

    /** 订单ID */
    @Excel(name = "订单ID")
    private Long orderId;

    /** 是否付款 */
    @Excel(name = "是否付款")
    private String isPay;

    /** 商品ID */
    @Excel(name = "商品ID")
    private Long productId;

    /** 商品SKU */
    @Excel(name = "商品SKU")
    private String productSku;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal unitPrice;

    /** 小计 */
    @Excel(name = "小计")
    private BigDecimal sumPrice;

    /** 数量 */
    @Excel(name = "数量")
    private Long count;

    /** 实付价格 */
    @Excel(name = "实付价格")
    private BigDecimal payPrice;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }
    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public Long getOrderId()
    {
        return orderId;
    }
    public void setIsPay(String isPay)
    {
        this.isPay = isPay;
    }

    public String getIsPay()
    {
        return isPay;
    }
    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getProductId()
    {
        return productId;
    }
    public void setProductSku(String productSku)
    {
        this.productSku = productSku;
    }

    public String getProductSku()
    {
        return productSku;
    }
    public void setUnitPrice(BigDecimal unitPrice)
    {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice()
    {
        return unitPrice;
    }
    public void setSumPrice(BigDecimal sumPrice)
    {
        this.sumPrice = sumPrice;
    }

    public BigDecimal getSumPrice()
    {
        return sumPrice;
    }
    public void setCount(Long count)
    {
        this.count = count;
    }

    public Long getCount()
    {
        return count;
    }
    public void setPayPrice(BigDecimal payPrice)
    {
        this.payPrice = payPrice;
    }

    public BigDecimal getPayPrice()
    {
        return payPrice;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderNumber", getOrderNumber())
            .append("orderId", getOrderId())
            .append("isPay", getIsPay())
            .append("productId", getProductId())
            .append("productSku", getProductSku())
            .append("unitPrice", getUnitPrice())
            .append("sumPrice", getSumPrice())
            .append("count", getCount())
            .append("payPrice", getPayPrice())
            .append("status", getStatus())
            .toString();
    }


}
