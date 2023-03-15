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
import com.bawei.mall.product.domain.MallProductReviewInfo;
import com.bawei.mall.product.service.IMallProductReviewInfoService;
import com.bawei.common.core.web.controller.BaseController;
import com.bawei.common.core.web.domain.AjaxResult;
import com.bawei.common.core.utils.poi.ExcelUtil;
import com.bawei.common.core.web.page.TableDataInfo;

/**
 * 商品评价Controller
 *
 * @author Luzhixuan
 * @date 2022-09-26
 */
@RestController
@RequestMapping("/review")
public class MallProductReviewInfoController extends BaseController
{
    @Autowired
    private IMallProductReviewInfoService mallProductReviewInfoService;

    /**
     * 查询商品评价列表
     */
    @RequiresPermissions("product:review:list")
    @GetMapping("/list")
    public TableDataInfo list(MallProductReviewInfo mallProductReviewInfo)
    {
        startPage();
        List<MallProductReviewInfo> list = mallProductReviewInfoService.selectMallProductReviewInfoList(mallProductReviewInfo);
        return getDataTable(list);
    }

    /**
     * 导出商品评价列表
     */
    @RequiresPermissions("product:review:export")
    @Log(title = "商品评价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MallProductReviewInfo mallProductReviewInfo)
    {
        List<MallProductReviewInfo> list = mallProductReviewInfoService.selectMallProductReviewInfoList(mallProductReviewInfo);
        ExcelUtil<MallProductReviewInfo> util = new ExcelUtil<MallProductReviewInfo>(MallProductReviewInfo.class);
        util.exportExcel(response, list, "商品评价数据");
    }

    /**
     * 获取商品评价详细信息
     */
    @RequiresPermissions("product:review:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(mallProductReviewInfoService.selectMallProductReviewInfoById(id));
    }

    /**
     * 新增商品评价
     */
    @RequiresPermissions("product:review:add")
    @Log(title = "商品评价", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MallProductReviewInfo mallProductReviewInfo)
    {
        return toAjax(mallProductReviewInfoService.insertMallProductReviewInfo(mallProductReviewInfo));
    }

    /**
     * 修改商品评价
     */
    @RequiresPermissions("product:review:edit")
    @Log(title = "商品评价", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MallProductReviewInfo mallProductReviewInfo)
    {
        return toAjax(mallProductReviewInfoService.updateMallProductReviewInfo(mallProductReviewInfo));
    }

    /**
     * 删除商品评价
     */
    @RequiresPermissions("product:review:remove")
    @Log(title = "商品评价", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mallProductReviewInfoService.deleteMallProductReviewInfoByIds(ids));
    }
}
