package com.bawei.mall.product.domain.model;

import com.bawei.common.core.exception.ServiceException;
import com.bawei.mall.product.domain.MallProductSkuInfo;

import java.util.List;

/**
 * @author LuZhixuan
 * @description: 批量添加sku
 * @Date 2022-9-24 上午 10:23
 */
public class SkuModel {

    private Long productId;

    private List<MallProductSkuInfo> skuInfoList;

    private SkuModel () {
    }

    private SkuModel (Long productId, List<MallProductSkuInfo> skuInfoList) {
        this.productId = productId;
        this.skuInfoList = skuInfoList;
    }

    public static SkuModel builderSkuModel(Long productId, List<MallProductSkuInfo> skuInfoList){
        if (productId == null){
            throw new ServiceException("商品ID不可为空");
        }

        return new SkuModel(productId, skuInfoList);
    }

    public Long getProductId () {
        return productId;
    }

    public void setProductId (Long productId) {
        this.productId = productId;
    }

    public List<MallProductSkuInfo> getSkuInfoList () {
        return skuInfoList;
    }

    public void setSkuInfoList (List<MallProductSkuInfo> skuInfoList) {
        this.skuInfoList = skuInfoList;
    }
}
