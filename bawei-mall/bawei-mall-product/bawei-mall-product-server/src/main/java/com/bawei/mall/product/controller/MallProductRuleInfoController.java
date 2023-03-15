package com.bawei.mall.product.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.bawei.common.core.domain.R;
import com.bawei.mall.product.domain.request.RuleRequest;
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
import com.bawei.mall.product.domain.MallProductRuleInfo;
import com.bawei.mall.product.service.IMallProductRuleInfoService;
import com.bawei.common.core.web.controller.BaseController;
import com.bawei.common.core.web.domain.AjaxResult;
import com.bawei.common.core.utils.poi.ExcelUtil;
import com.bawei.common.core.web.page.TableDataInfo;

/**
 * 商品规格Controller
 *
 * @author Luzhixuan
 * @date 2023-02-30
 */
@RestController
@RequestMapping("/rule")
public class MallProductRuleInfoController extends BaseController
{
    @Autowired
    private IMallProductRuleInfoService mallProductRuleInfoService;

    /**
     * 查询商品规格列表
     */
    @RequiresPermissions("product:rule:list")
    @GetMapping("/list")
    public TableDataInfo list(MallProductRuleInfo mallProductRuleInfo)
    {
        startPage();
        List<MallProductRuleInfo> list = mallProductRuleInfoService.selectMallProductRuleInfoList(mallProductRuleInfo);
        return getDataTable(list);
    }
    @RequiresPermissions("product:rule:list")
    @GetMapping("/all")
    public R all()
    {
        List<MallProductRuleInfo> list = mallProductRuleInfoService.selectMallProductRuleInfoList(new MallProductRuleInfo());
        return R.ok(list);
    }

    /**
     * 导出商品规格列表
     */
    @RequiresPermissions("product:rule:export")
    @Log(title = "商品规格", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MallProductRuleInfo mallProductRuleInfo)
    {
        List<MallProductRuleInfo> list = mallProductRuleInfoService.selectMallProductRuleInfoList(mallProductRuleInfo);
        ExcelUtil<MallProductRuleInfo> util = new ExcelUtil<MallProductRuleInfo>(MallProductRuleInfo.class);
        util.exportExcel(response, list, "商品规格数据");
    }

    /**
     * 获取商品规格详细信息
     */
    @RequiresPermissions("product:rule:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(mallProductRuleInfoService.selectMallProductRuleInfoById(id));
    }

    /**
     * 新增商品规格
     */
    @RequiresPermissions("product:rule:add")
    @Log(title = "商品规格", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RuleRequest ruleRequest)
    {
        return toAjax(mallProductRuleInfoService.insertMallProductRuleInfo(ruleRequest));
    }

    /**
     * 修改商品规格
     */
    @RequiresPermissions("product:rule:edit")
    @Log(title = "商品规格", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RuleRequest ruleRequest)
    {
        return toAjax(mallProductRuleInfoService.updateMallProductRuleInfo(ruleRequest));
    }

    /**
     * 删除商品规格
     */
    @RequiresPermissions("product:rule:remove")
    @Log(title = "商品规格", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mallProductRuleInfoService.deleteMallProductRuleInfoByIds(ids));
    }
}
