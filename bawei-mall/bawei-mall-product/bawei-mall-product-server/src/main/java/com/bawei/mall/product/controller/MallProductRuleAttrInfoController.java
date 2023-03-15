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
import com.bawei.mall.product.domain.MallProductRuleAttrInfo;
import com.bawei.mall.product.service.IMallProductRuleAttrInfoService;
import com.bawei.common.core.web.controller.BaseController;
import com.bawei.common.core.web.domain.AjaxResult;
import com.bawei.common.core.utils.poi.ExcelUtil;
import com.bawei.common.core.web.page.TableDataInfo;

/**
 * 商品规格详情Controller
 *
 * @author Luzhixuan
 * @date 2023-02-30
 */
@RestController
@RequestMapping("/attr")
public class MallProductRuleAttrInfoController extends BaseController
{
    @Autowired
    private IMallProductRuleAttrInfoService mallProductRuleAttrInfoService;

    /**
     * 查询商品规格详情列表
     */
    @RequiresPermissions("product:attr:list")
    @GetMapping("/list")
    public TableDataInfo list(MallProductRuleAttrInfo mallProductRuleAttrInfo)
    {
        startPage();
        List<MallProductRuleAttrInfo> list = mallProductRuleAttrInfoService.selectMallProductRuleAttrInfoList(mallProductRuleAttrInfo);
        return getDataTable(list);
    }

    /**
     * 导出商品规格详情列表
     */
    @RequiresPermissions("product:attr:export")
    @Log(title = "商品规格详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MallProductRuleAttrInfo mallProductRuleAttrInfo)
    {
        List<MallProductRuleAttrInfo> list = mallProductRuleAttrInfoService.selectMallProductRuleAttrInfoList(mallProductRuleAttrInfo);
        ExcelUtil<MallProductRuleAttrInfo> util = new ExcelUtil<MallProductRuleAttrInfo>(MallProductRuleAttrInfo.class);
        util.exportExcel(response, list, "商品规格详情数据");
    }

    /**
     * 获取商品规格详情详细信息
     */
    @RequiresPermissions("product:attr:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(mallProductRuleAttrInfoService.selectMallProductRuleAttrInfoById(id));
    }

    /**
     * 新增商品规格详情
     */
    @RequiresPermissions("product:attr:add")
    @Log(title = "商品规格详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MallProductRuleAttrInfo mallProductRuleAttrInfo)
    {
        return toAjax(mallProductRuleAttrInfoService.insertMallProductRuleAttrInfo(mallProductRuleAttrInfo));
    }

    /**
     * 修改商品规格详情
     */
    @RequiresPermissions("product:attr:edit")
    @Log(title = "商品规格详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MallProductRuleAttrInfo mallProductRuleAttrInfo)
    {
        return toAjax(mallProductRuleAttrInfoService.updateMallProductRuleAttrInfo(mallProductRuleAttrInfo));
    }

    /**
     * 删除商品规格详情
     */
    @RequiresPermissions("product:attr:remove")
    @Log(title = "商品规格详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mallProductRuleAttrInfoService.deleteMallProductRuleAttrInfoByIds(ids));
    }
}
