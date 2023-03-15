package com.bawei.mall.product.domain.model;

import com.bawei.common.core.annotation.Excel;
import com.bawei.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiParam;

/**
 * @author LuZhixuan
 * @description: 商品详情 - 基本信息
 * @Date 2022-10-18 下午 01:57
 */
public class ProductModel extends BaseEntity {

    /** ID */
    private Long id;

    /** 商品名称 */
    private String name;

    /** 商品描述 */
    private String productDesc;

    /** 商品类型 */
    private String type;
    private String typeName;

    /** 冗余字段 */
    private String typeIds;

    /** 商品主图 */
    private String img;

    /** 商品轮播图 */
    private String carouselImages;

    /** 商品评论数 */
    private Long commentCount;

    /** 商品收藏人气 */
    private Long collectCount;

    /** 品牌信息 */
    private String brand;
    private String brandName;

    /** 商品状态 */
    private String status;

    /** 单位 */
    private String unit;

    /** 搜索关键字 */
    private String keywords;

    /** 规格信息 */
    private Long ruleId;

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getProductDesc () {
        return productDesc;
    }

    public void setProductDesc (String productDesc) {
        this.productDesc = productDesc;
    }

    public String getType () {
        return type;
    }

    public void setType (String type) {
        this.type = type;
    }

    public String getTypeName () {
        return typeName;
    }

    public void setTypeName (String typeName) {
        this.typeName = typeName;
    }

    public String getTypeIds () {
        return typeIds;
    }

    public void setTypeIds (String typeIds) {
        this.typeIds = typeIds;
    }

    public String getImg () {
        return img;
    }

    public void setImg (String img) {
        this.img = img;
    }

    public String getCarouselImages () {
        return carouselImages;
    }

    public void setCarouselImages (String carouselImages) {
        this.carouselImages = carouselImages;
    }

    public Long getCommentCount () {
        return commentCount;
    }

    public void setCommentCount (Long commentCount) {
        this.commentCount = commentCount;
    }

    public Long getCollectCount () {
        return collectCount;
    }

    public void setCollectCount (Long collectCount) {
        this.collectCount = collectCount;
    }

    public String getBrand () {
        return brand;
    }

    public void setBrand (String brand) {
        this.brand = brand;
    }

    public String getBrandName () {
        return brandName;
    }

    public void setBrandName (String brandName) {
        this.brandName = brandName;
    }

    public String getStatus () {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
    }

    public String getUnit () {
        return unit;
    }

    public void setUnit (String unit) {
        this.unit = unit;
    }

    public String getKeywords () {
        return keywords;
    }

    public void setKeywords (String keywords) {
        this.keywords = keywords;
    }

    public Long getRuleId () {
        return ruleId;
    }

    public void setRuleId (Long ruleId) {
        this.ruleId = ruleId;
    }
}
