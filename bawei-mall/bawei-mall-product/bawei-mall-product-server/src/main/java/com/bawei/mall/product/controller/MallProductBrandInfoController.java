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
import com.bawei.mall.product.domain.MallProductBrandInfo;
import com.bawei.mall.product.service.IMallProductBrandInfoService;
import com.bawei.common.core.web.controller.BaseController;
import com.bawei.common.core.web.domain.AjaxResult;
import com.bawei.common.core.utils.poi.ExcelUtil;
import com.bawei.common.core.web.page.TableDataInfo;

/**
 * 商品品牌Controller
 *
 * @author Luzhixuan
 * @date 2022-09-15
 */
@RestController
@RequestMapping("/brand")
public class MallProductBrandInfoController extends BaseController
{
    @Autowired
    private IMallProductBrandInfoService mallProductBrandInfoService;

    /**
     * 查询商品品牌列表
     */
    @RequiresPermissions("product:brand:list")
    @GetMapping("/list")
    public TableDataInfo list(MallProductBrandInfo mallProductBrandInfo)
    {
        startPage();
        List<MallProductBrandInfo> list = mallProductBrandInfoService.selectMallProductBrandInfoList(mallProductBrandInfo);
        return getDataTable(list);
    }

    /**
     * 导出商品品牌列表
     */
    @RequiresPermissions("product:brand:export")
    @Log(title = "商品品牌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MallProductBrandInfo mallProductBrandInfo)
    {
        List<MallProductBrandInfo> list = mallProductBrandInfoService.selectMallProductBrandInfoList(mallProductBrandInfo);
        ExcelUtil<MallProductBrandInfo> util = new ExcelUtil<MallProductBrandInfo>(MallProductBrandInfo.class);
        util.exportExcel(response, list, "商品品牌数据");
    }

    /**
     * 获取商品品牌详细信息
     */
    @RequiresPermissions("product:brand:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(mallProductBrandInfoService.selectMallProductBrandInfoById(id));
    }

    /**
     * 新增商品品牌
     */
    @RequiresPermissions("product:brand:add")
    @Log(title = "商品品牌", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MallProductBrandInfo mallProductBrandInfo)
    {
        return toAjax(mallProductBrandInfoService.insertMallProductBrandInfo(mallProductBrandInfo));
    }

    /**
     * 修改商品品牌
     */
    @RequiresPermissions("product:brand:edit")
    @Log(title = "商品品牌", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MallProductBrandInfo mallProductBrandInfo)
    {
        return toAjax(mallProductBrandInfoService.updateMallProductBrandInfo(mallProductBrandInfo));
    }

    /**
     * 删除商品品牌
     */
    @RequiresPermissions("product:brand:remove")
    @Log(title = "商品品牌", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mallProductBrandInfoService.deleteMallProductBrandInfoByIds(ids));
    }
}
