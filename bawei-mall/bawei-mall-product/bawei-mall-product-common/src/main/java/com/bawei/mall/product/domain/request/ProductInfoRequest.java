package com.bawei.mall.product.domain.request;

import com.bawei.mall.product.domain.MallProductInfo;
import com.bawei.mall.product.domain.MallProductSkuInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

import java.util.List;

/**
 * @author LuZhixuan
 * @description: 商品信息入参
 * @Date 2022-9-24 上午 10:34
 */
public class ProductInfoRequest extends MallProductInfo {

    @ApiParam("sku集合")
    private List<MallProductSkuInfo> skuInfoList;

    public List<MallProductSkuInfo> getSkuInfoList () {
        return skuInfoList;
    }

    public void setSkuInfoList (List<MallProductSkuInfo> skuInfoList) {
        this.skuInfoList = skuInfoList;
    }
}
