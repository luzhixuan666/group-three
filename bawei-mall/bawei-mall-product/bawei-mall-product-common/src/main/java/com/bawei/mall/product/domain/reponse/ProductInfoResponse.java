package com.bawei.mall.product.domain.reponse;

import com.bawei.mall.product.domain.MallProductInfo;
import com.bawei.mall.product.domain.MallProductSkuInfo;

import java.util.List;

/**
 * @author LuZhixuan
 * @description:
 * @Date 2022-9-24 上午 11:27
 */
public class ProductInfoResponse extends MallProductInfo {

    private List<MallProductSkuInfo> skuInfoList;

    public List<MallProductSkuInfo> getSkuInfoList () {
        return skuInfoList;
    }

    public void setSkuInfoList (List<MallProductSkuInfo> skuInfoList) {
        this.skuInfoList = skuInfoList;
    }
}
