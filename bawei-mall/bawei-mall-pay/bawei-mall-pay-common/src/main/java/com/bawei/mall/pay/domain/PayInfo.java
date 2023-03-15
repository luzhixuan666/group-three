package com.bawei.mall.pay.domain;

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
 * 支付记录对象 pay_info
 *
 * @author Luzhixuan
 * @date 2023-03-08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderNumber;

    /** 支付类型 */
    @Excel(name = "支付类型")
    private String payType;

    /** 支付完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "支付完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payTime;

    /** 支付状态 */
    @Excel(name = "支付状态")
    private String status;

    /** 支付金额 */
    @Excel(name = "支付金额")
    private BigDecimal payPrice;

    /** $column.columnComment */
    private String resultJson;

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
    public void setPayType(String payType)
    {
        this.payType = payType;
    }

    public String getPayType()
    {
        return payType;
    }
    public void setPayTime(Date payTime)
    {
        this.payTime = payTime;
    }

    public Date getPayTime()
    {
        return payTime;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setPayPrice(BigDecimal payPrice)
    {
        this.payPrice = payPrice;
    }

    public BigDecimal getPayPrice()
    {
        return payPrice;
    }
    public void setResultJson(String resultJson)
    {
        this.resultJson = resultJson;
    }

    public String getResultJson()
    {
        return resultJson;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderNumber", getOrderNumber())
            .append("createTime", getCreateTime())
            .append("payType", getPayType())
            .append("payTime", getPayTime())
            .append("createBy", getCreateBy())
            .append("status", getStatus())
            .append("payPrice", getPayPrice())
            .append("resultJson", getResultJson())
            .toString();
    }
}
