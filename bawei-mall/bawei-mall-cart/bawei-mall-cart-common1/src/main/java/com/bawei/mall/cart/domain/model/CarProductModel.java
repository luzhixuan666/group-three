package com.bawei.mall.cart.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarProductModel {
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 购买数量
     */
    private Long count;

    /**
     * 是否选中
     */
    private String check;

    /**
     * 购物车ID
     */
    private Long carInfoId;
}
