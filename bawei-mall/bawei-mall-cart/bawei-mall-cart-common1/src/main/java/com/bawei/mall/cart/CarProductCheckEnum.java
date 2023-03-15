package com.bawei.mall.cart;


/**
 * 购物车商品是否选中枚举
 */
public enum  CarProductCheckEnum {
    CHECK("Y","已选中"),

    NO_CHECK("N","未选中");
    private final String code;


    private final String lable;


    CarProductCheckEnum(String code, String lable) {
        this.code = code;
        this.lable = lable;
    }

    public final String code(){
        return this.code;
    }

    public final String lable(){
        return this.lable;
    }



}
