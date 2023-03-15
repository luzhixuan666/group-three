package com.bawei.mall.product.remote;

import com.bawei.common.core.constant.ServiceNameConstants;
import com.bawei.common.core.domain.R;
import com.bawei.common.core.web.domain.AjaxResult;
import com.bawei.common.core.web.page.TableDataInfo;
import com.bawei.mall.product.domain.MallProductInfo;
import com.bawei.mall.product.domain.reponse.ProductDetailsResponse;
import com.bawei.mall.product.domain.reponse.ProductInfoResponse;
import com.bawei.mall.product.factory.RemoteProductInfoFallbackFactory;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(contextId = "remoteProductInfoService", value = ServiceNameConstants.PRODUCT_SERVICE,
        fallbackFactory = RemoteProductInfoFallbackFactory.class,
        path = "/info")
public interface RemoteProductInfo {

    /**
     * 查询商品信息列表
     */
    @PostMapping("/syncList")
    public TableDataInfo syncList(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int syncNum,
                              MallProductInfo mallProductInfo);

    /**
     * 获取商品详情
     * @param id
     * @return
     */
    @GetMapping("/details/{id}")
    public R<ProductDetailsResponse> getProductResponse(@PathVariable("id") Long id);

    /**
     * 查询商品信息总条数
     */
    @PostMapping("/count")
    public R<Long> count(@RequestHeader(name = "get-type",value = "get-type")String getType,
                         @RequestBody MallProductInfo mallProductInfo);

    /**
     * 获取商品信息详细信息
     */
    @GetMapping(value = "/result/{id}")
    public R<ProductInfoResponse> getResultInfo(@PathVariable("id") Long id);


    /**
     * 获取商品信息详细信息
     */
    @PostMapping(value = "/lockProductStock")
    public R lockProductStock();


}
