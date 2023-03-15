package com.bawei.mall.pay.controller;

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
import com.bawei.mall.pay.domain.PayInfo;
import com.bawei.mall.pay.service.IPayInfoService;
import com.bawei.common.core.web.controller.BaseController;
import com.bawei.common.core.web.domain.AjaxResult;
import com.bawei.common.core.utils.poi.ExcelUtil;
import com.bawei.common.core.web.page.TableDataInfo;

/**
 * 支付记录Controller
 *
 * @author Luzhixuan
 * @date 2023-03-08
 */
@RestController
@RequestMapping("/info")
public class PayInfoController extends BaseController
{
    @Autowired
    private IPayInfoService payInfoService;

    /**
     * 查询支付记录列表
     */
    @RequiresPermissions("pay:info:list")
    @GetMapping("/list")
    public TableDataInfo list(PayInfo payInfo)
    {
        startPage();
        List<PayInfo> list = payInfoService.selectPayInfoList(payInfo);
        return getDataTable(list);
    }

    /**
     * 导出支付记录列表
     */
    @RequiresPermissions("pay:info:export")
    @Log(title = "支付记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PayInfo payInfo)
    {
        List<PayInfo> list = payInfoService.selectPayInfoList(payInfo);
        ExcelUtil<PayInfo> util = new ExcelUtil<PayInfo>(PayInfo.class);
        util.exportExcel(response, list, "支付记录数据");
    }

    /**
     * 获取支付记录详细信息
     */
    @RequiresPermissions("pay:info:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(payInfoService.selectPayInfoById(id));
    }

    /**
     * 新增支付记录
     */
    @RequiresPermissions("pay:info:add")
    @Log(title = "支付记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PayInfo payInfo)
    {
        return toAjax(payInfoService.insertPayInfo(payInfo));
    }

    /**
     * 修改支付记录
     */
    @RequiresPermissions("pay:info:edit")
    @Log(title = "支付记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PayInfo payInfo)
    {
        return toAjax(payInfoService.updatePayInfo(payInfo));
    }

    /**
     * 删除支付记录
     */
    @RequiresPermissions("pay:info:remove")
    @Log(title = "支付记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(payInfoService.deletePayInfoByIds(ids));
    }
}
