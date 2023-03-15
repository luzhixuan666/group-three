package com.bawei.mall.cart.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.bawei.common.core.domain.R;
import com.bawei.mall.cart.domain.model.*;
import com.bawei.mall.cart.domain.request.*;
import com.bawei.mall.cart.domain.respone.CarInfoRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bawei.common.log.annotation.Log;
import com.bawei.common.log.enums.BusinessType;
import com.bawei.common.security.annotation.RequiresPermissions;
import com.bawei.mall.cart.domain.CartInfo;
import com.bawei.mall.cart.service.ICartInfoService;
import com.bawei.common.core.web.controller.BaseController;
import com.bawei.common.core.web.domain.AjaxResult;
import com.bawei.common.core.utils.poi.ExcelUtil;


/**
 * 购物车Controller
 *
 * @author Luzhixuan
 * @date 2023-01-30
 */
@RestController
@RequestMapping("/cart")
public class CartInfoController extends BaseController
{
    @Autowired
    private ICartInfoService cartInfoService;

    /**
     * 查询购物车列表
     */
    @RequiresPermissions("cart:cart:list")
    @GetMapping("/list")
    public R list()
    {
        CarInfoRespone carInfoRespone= cartInfoService.cartInfoAllList();
        return R.ok(carInfoRespone);
    }

    /**
     * 导出购物车列表
     */
    @RequiresPermissions("cart:cart:export")
    @Log(title = "购物车", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CartInfo cartInfo)
    {
        List<CartInfo> list = cartInfoService.selectCartInfoList(cartInfo);
        ExcelUtil<CartInfo> util = new ExcelUtil<CartInfo>(CartInfo.class);
        util.exportExcel(response, list, "购物车数据");
    }

    /**
     * 获取购物车详细信息
     */
    @RequiresPermissions("cart:cart:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(cartInfoService.selectCartInfoById(id));
    }


    /**
     * 修改购物车
     */
    @RequiresPermissions("cart:cart:edit")
    @Log(title = "购物车", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CartInfo cartInfo)
    {
        return toAjax(cartInfoService.updateCartInfo(cartInfo));
    }

    /**
     * 删除购物车
     */
    @RequiresPermissions("cart:cart:remove")
    @Log(title = "购物车", businessType = BusinessType.DELETE)
	@DeleteMapping
    public R remove(@RequestBody @Validated List<RemoveCarProductRequest> removeCarProductRequestList)
    {
        this.cartInfoService.deleteCartInfo(
                removeCarProductRequestList.stream().map(removeCarProductRequest ->
                RemoveCarProductModel.builder()
                        .productId(removeCarProductRequest.productId)
                        .productSku(removeCarProductRequest.getProductSku())
                        .build()
                ).collect(Collectors.toList())
        );
        return R.ok();
    }


    /**
     * 添加到购物车
     * @param addCarRequest
     * @return
     */
    @RequiresPermissions("cart:cart:add")
    @Log(title = "购物车", businessType = BusinessType.INSERT)
    @PostMapping
    public R addCar(@RequestBody @Validated AddCarRequest addCarRequest){
        this.cartInfoService.addCar(
                AddCarModel.builder().productId(addCarRequest.getProductId())
                        .productSku(addCarRequest.getProductSku())
                        .count(addCarRequest.getCount()).build()
        );
        return R.ok();
    }

    @PostMapping("/check")
    public R carProductCheck(@RequestBody @Validated CarProductCheckRequest carProductCheckRequest){
        this.cartInfoService.carCheckProduct(
                CarCheckProductModel.builder()
                        .productId(carProductCheckRequest.getProductId())
                        .productSku(carProductCheckRequest.getProductSku())
                        .check(carProductCheckRequest.getCheck()).build()
        );
        return R.ok();
    }

    @PutMapping("/checkSku")
    public R checkSku(@RequestBody @Validated CheckSkuRequest checkSkuRequest){
        this.cartInfoService.checkSku(
                CheckSkuModel.builder()
                        .productId(checkSkuRequest.getProductId())
                        .newSku(checkSkuRequest.getNewSku())
                        .oldSku(checkSkuRequest.getOldSku())
                        .build()
        );
        return R.ok();
    }


    @PutMapping("/updateSkuCount")
    public R updateSkuCount(@RequestBody @Validated UpdateSkuCountRequest updateSkuCountRequest){
        this.cartInfoService.updateSkuCount(
                UpdateSkuCountModel.builder()
                        .productId(updateSkuCountRequest.productId)
                        .count(updateSkuCountRequest.getCount())
                        .productSku(updateSkuCountRequest.getProductSku())
                        .build()
        );
        return R.ok();
    }

    /**
     * 清空购物车选中商品
     * @return
     */
    @DeleteMapping("/clear")
    public R clearCarchangeProduct(){
        this.cartInfoService.clearCarChangeProduct();
        return R.ok();
    }
}
