package com.bawei.mall.order.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.bawei.common.core.annotation.Excel;
import com.bawei.common.core.web.domain.BaseEntity;

/**
 * 订单对象 order_info
 *
 * @author LuZhiXuan
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 收货地址 */
    @Excel(name = "收货地址")
    private Long addressId;

    /** 数量 */
    @Excel(name = "数量")
    private Long count;

    /** 商品总价 */
    @Excel(name = "商品总价")
    private BigDecimal sumPrice;

    /** 支付价格 */
    @Excel(name = "支付价格")
    private BigDecimal payPrice;

    /** 优惠价格 */
    @Excel(name = "优惠价格")
    private BigDecimal discountPrice;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private String status;

    /** 截止时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payDeadlineTime;

    /** 支付结果 */
    @Excel(name = "支付结果")
    private String payResult;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String number;

    /** 订单token */
    private String orderToken;
    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setAddressId(Long addressId)
    {
        this.addressId = addressId;
    }

    public Long getAddressId()
    {
        return addressId;
    }
    public void setCount(Long count)
    {
        this.count = count;
    }

    public Long getCount()
    {
        return count;
    }
    public void setSumPrice(BigDecimal sumPrice)
    {
        this.sumPrice = sumPrice;
    }

    public BigDecimal getSumPrice()
    {
        return sumPrice;
    }
    public void setPayPrice(BigDecimal payPrice)
    {
        this.payPrice = payPrice;
    }

    public BigDecimal getPayPrice()
    {
        return payPrice;
    }
    public void setDiscountPrice(BigDecimal discountPrice)
    {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getDiscountPrice()
    {
        return discountPrice;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setPayDeadlineTime(Date payDeadlineTime)
    {
        this.payDeadlineTime = payDeadlineTime;
    }

    public Date getPayDeadlineTime()
    {
        return payDeadlineTime;
    }
    public void setPayResult(String payResult)
    {
        this.payResult = payResult;
    }

    public String getPayResult()
    {
        return payResult;
    }
    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getNumber()
    {
        return number;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("addressId", getAddressId())
            .append("count", getCount())
            .append("sumPrice", getSumPrice())
            .append("payPrice", getPayPrice())
            .append("discountPrice", getDiscountPrice())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("status", getStatus())
            .append("payDeadlineTime", getPayDeadlineTime())
            .append("payResult", getPayResult())
            .append("number", getNumber())
            .toString();
    }
}
