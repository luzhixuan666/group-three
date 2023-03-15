package com.bawei.mall.product.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;

import com.bawei.common.core.domain.R;
import com.bawei.mall.product.domain.reponse.ProductDetailsResponse;
import com.bawei.mall.product.domain.reponse.ProductInfoResponse;
import com.bawei.mall.product.domain.request.ProductInfoRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bawei.common.log.annotation.Log;
import com.bawei.common.log.enums.BusinessType;
import com.bawei.common.security.annotation.RequiresPermissions;
import com.bawei.mall.product.domain.MallProductInfo;
import com.bawei.mall.product.service.IMallProductInfoService;
import com.bawei.common.core.web.controller.BaseController;
import com.bawei.common.core.web.domain.AjaxResult;
import com.bawei.common.core.utils.poi.ExcelUtil;
import com.bawei.common.core.web.page.TableDataInfo;
import redis.clients.jedis.Jedis;

/**
 * 商品信息Controller
 *
 * @author Luzhixuan
 * @date 2023-02-30
 */
@RestController
@RequestMapping("/info")
@Api("商品维护 - API")
public class MallProductInfoController extends BaseController
{
    @Autowired
    private IMallProductInfoService mallProductInfoService;

    Jedis jedis = new Jedis("106.14.46.21", 6379);

    /**
     * 查询商品信息列表
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public TableDataInfo list(MallProductInfo mallProductInfo)
    {
        startPage();
        List<MallProductInfo> list = mallProductInfoService.selectMallProductInfoList(mallProductInfo);
        return getDataTable(list);
    }

    /**
     * 热点排序
     */
    @GetMapping("/hostlist")
    public R<List<MallProductInfo>> hostlist(){
        //获取热度指标最高的商品,返回前100条热点数据
        List<String> set = jedis.zrevrange("host_final", 0, 100);
        //根据商品Id的查询商品品牌信息
        ArrayList<MallProductInfo> list = new ArrayList<>();
        for (String id : set) {
            ProductInfoResponse productInfo = mallProductInfoService.selectMallProductInfoById(Long.valueOf(id));
            list.add(productInfo);
        }
        jedis.close();
        return R.ok(list);
    }



    @PostMapping("/syncList")
    public TableDataInfo syncList(@RequestBody MallProductInfo mallProductInfo)
    {
        startPage();
        List<MallProductInfo> list = mallProductInfoService.selectMallProductInfoList(mallProductInfo);
        return getDataTable(list);
    }


    /**
     * 锁定库存
     * @return
     */
    @PostMapping("/lockProductStock")
    public R lockProductStock()
    {
        this.mallProductInfoService.lockProductStock();
        return R.ok();
    }


    /**
     * 查询商品信息总条数
     */
    @RequiresPermissions("product:info:list")
    @PostMapping("/count")
    public R<Long> count(@RequestBody MallProductInfo mallProductInfo)
    {
        return R.ok(mallProductInfoService.selectMallProductInfoCount(mallProductInfo));
    }



    /**
     * 导出商品信息列表
     */
    @RequiresPermissions("product:info:export")
    @Log(title = "商品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MallProductInfo mallProductInfo)
    {
        List<MallProductInfo> list = mallProductInfoService.selectMallProductInfoList(mallProductInfo);
        ExcelUtil<MallProductInfo> util = new ExcelUtil<MallProductInfo>(MallProductInfo.class);
        util.exportExcel(response, list, "商品信息数据");
    }

    /**
     * 获取商品信息详细信息
     */
    @RequiresPermissions("product:info:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(mallProductInfoService.selectMallProductInfoById(id));
    }

    @GetMapping(value = "/result/{id}")
    public R<ProductInfoResponse> getResultInfo(@PathVariable("id") Long id)
    {
        return R.ok(mallProductInfoService.selectMallProductInfoById(id));
    }

    /**
     * 获取商品详情
     * @param id
     * @return
     */
    @GetMapping("/details/{id}")
    public R<ProductDetailsResponse> getProductResponse(@PathVariable("id") Long id){
        return R.ok(mallProductInfoService.selectProductDetailsById(id));
    }

    /**
     * 新增商品信息
     */
    @RequiresPermissions("product:info:add")
    @Log(title = "商品信息", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("商品添加")
    public AjaxResult add(@RequestBody @ApiParam("商品请求实体类") ProductInfoRequest productInfoRequest)
    {
        return toAjax(mallProductInfoService.insertMallProductInfo(productInfoRequest));
    }

    /**
     * 修改商品信息
     */
    @RequiresPermissions("product:info:edit")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductInfoRequest productInfoRequest)
    {
        return toAjax(mallProductInfoService.updateMallProductInfo(productInfoRequest));
    }

    /**
     * 删除商品信息
     */
    @RequiresPermissions("product:info:remove")
    @Log(title = "商品信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mallProductInfoService.deleteMallProductInfoByIds(ids));
    }
}
