package com.bawei.mall.product.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.bawei.mall.product.domain.MallProductSkuInfo;
import com.bawei.mall.product.service.IMallProductSkuInfoService;
import com.bawei.common.core.web.controller.BaseController;
import com.bawei.common.core.web.domain.AjaxResult;
import com.bawei.common.core.utils.poi.ExcelUtil;
import com.bawei.common.core.web.page.TableDataInfo;

/**
 * 商品SKUController
 *
 * @author Luzhixuan
 * @date 2023-02-30
 */
@RestController
@RequestMapping("/sku")
public class MallProductSkuInfoController extends BaseController
{
    @Autowired
    private IMallProductSkuInfoService mallProductSkuInfoService;

    /**
     * 查询商品SKU列表
     */
    @RequiresPermissions("product:sku:list")
    @GetMapping("/list")
    public TableDataInfo list(MallProductSkuInfo mallProductSkuInfo)
    {
        startPage();
        List<MallProductSkuInfo> list = mallProductSkuInfoService.selectMallProductSkuInfoList(mallProductSkuInfo);
        return getDataTable(list);
    }

    /**
     * 导出商品SKU列表
     */
    @RequiresPermissions("product:sku:export")
    @Log(title = "商品SKU", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MallProductSkuInfo mallProductSkuInfo)
    {
        List<MallProductSkuInfo> list = mallProductSkuInfoService.selectMallProductSkuInfoList(mallProductSkuInfo);
        ExcelUtil<MallProductSkuInfo> util = new ExcelUtil<MallProductSkuInfo>(MallProductSkuInfo.class);
        util.exportExcel(response, list, "商品SKU数据");
    }

    /**
     * 获取商品SKU详细信息
     */
    @RequiresPermissions("product:sku:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(mallProductSkuInfoService.selectMallProductSkuInfoById(id));
    }

    /**
     * 新增商品SKU
     */
    @RequiresPermissions("product:sku:add")
    @Log(title = "商品SKU", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MallProductSkuInfo mallProductSkuInfo)
    {
        return toAjax(mallProductSkuInfoService.insertMallProductSkuInfo(mallProductSkuInfo));
    }

    /**
     * 修改商品SKU
     */
    @RequiresPermissions("product:sku:edit")
    @Log(title = "商品SKU", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MallProductSkuInfo mallProductSkuInfo)
    {
        return toAjax(mallProductSkuInfoService.updateMallProductSkuInfo(mallProductSkuInfo));
    }

    /**
     * 删除商品SKU
     */
    @RequiresPermissions("product:sku:remove")
    @Log(title = "商品SKU", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mallProductSkuInfoService.deleteMallProductSkuInfoByIds(ids));
    }
}
