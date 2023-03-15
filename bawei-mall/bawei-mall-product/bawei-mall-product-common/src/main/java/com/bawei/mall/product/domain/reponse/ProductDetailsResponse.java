package com.bawei.mall.product.domain.reponse;

import com.bawei.mall.product.domain.MallProductRuleInfo;
import com.bawei.mall.product.domain.MallProductSkuInfo;
import com.bawei.mall.product.domain.model.ProductModel;

import java.util.List;

/**
 * @author LuZhixuan
 * @description: 商品详情
 * @Date 2022-10-18 下午 02:00
 */
public class ProductDetailsResponse {

    /**
     * 商品基本信息
     */
    private ProductModel product;

    /**
     * 商品的sku信息
     */
    private List<MallProductSkuInfo> skuList;

    /**
     * 商品规格信息
     */
    private MallProductRuleInfo productRule;

    public ProductModel getProduct () {
        return product;
    }

    public void setProduct (ProductModel product) {
        this.product = product;
    }

    public List<MallProductSkuInfo> getSkuList () {
        return skuList;
    }

    public void setSkuList (List<MallProductSkuInfo> skuList) {
        this.skuList = skuList;
    }

    public MallProductRuleInfo getProductRule () {
        return productRule;
    }

    public void setProductRule (MallProductRuleInfo productRule) {
        this.productRule = productRule;
    }
}
