package com.bawei.mall.cart.domain;

import com.bawei.common.core.constant.CarConstants;
import com.bawei.mall.cart.domain.model.CarProductModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.bawei.common.core.annotation.Excel;
import com.bawei.common.core.web.domain.BaseEntity;

/**
 * 购物车对象 cart_info
 *
 * @author Luzhixuan
 * @date 2023-01-30
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 商品 */
    @Excel(name = "商品")
    private Long productId;

    /** SKU */
    @Excel(name = "SKU")
    private String productSku;

    /** 购买数量 */
    @Excel(name = "购买数量")
    private Long count;

    /** 是否选中 */
    @Excel(name = "是否选中")
    private String check;


    public static CartInfo carProductModelBuild(String carProductKey, CarProductModel carProductModel) {
        CartInfo cartInfo=CartInfo.builder()
                .id(carProductModel.getCarInfoId())
                .check(carProductModel.getCheck())
                .count(carProductModel.getCount()).build();
        String[] split = carProductKey.split(CarConstants.CAR_PRODUCT_KEY);
        cartInfo.setProductId(Long.valueOf(split[0]));
        cartInfo.setProductSku(split[1]);
        return cartInfo;
    }

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
    public void setProductSku(String productSku)
    {
        this.productSku = productSku;
    }

    public String getProductSku()
    {
        return productSku;
    }
    public void setCount(Long count)
    {
        this.count = count;
    }

    public Long getCount()
    {
        return count;
    }
    public void setCheck(String check)
    {
        this.check = check;
    }

    public String getCheck()
    {
        return check;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("productId", getProductId())
                .append("productSku", getProductSku())
                .append("count", getCount())
                .append("check", getCheck())
                .append("createTime", getCreateTime())
                .append("createBy", getCreateBy())
                .toString();
    }
}
