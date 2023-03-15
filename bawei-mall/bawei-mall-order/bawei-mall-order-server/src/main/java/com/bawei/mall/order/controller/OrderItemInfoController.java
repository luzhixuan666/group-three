package com.bawei.mall.order.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.bawei.common.core.domain.R;
import com.bawei.mall.order.domain.OrderItemInfo;
import com.bawei.mall.order.service.IOrderItemInfoService;
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
import com.bawei.common.core.web.controller.BaseController;
import com.bawei.common.core.web.domain.AjaxResult;
import com.bawei.common.core.utils.poi.ExcelUtil;
import com.bawei.common.core.web.page.TableDataInfo;

/**
 * 订单明细Controller
 *
 * @author Luzhixuan
 * @date 2023-02-09
 */
@RestController
@RequestMapping("/item")
public class OrderItemInfoController extends BaseController
{
    @Autowired
    private IOrderItemInfoService orderItemInfoService;

    /**
     * 查询订单明细列表
     */
    @RequiresPermissions("order:item:list")
    @GetMapping("/list")
    public TableDataInfo list(OrderItemInfo orderItemInfo)
    {
        startPage();
        List<OrderItemInfo> list = orderItemInfoService.selectOrderItemInfoList(orderItemInfo);
        return getDataTable(list);
    }

    /**
     * 导出订单明细列表
     */
    @RequiresPermissions("order:item:export")
    @Log(title = "订单明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrderItemInfo orderItemInfo)
    {
        List<OrderItemInfo> list = orderItemInfoService.selectOrderItemInfoList(orderItemInfo);
        ExcelUtil<OrderItemInfo> util = new ExcelUtil<OrderItemInfo>(OrderItemInfo.class);
        util.exportExcel(response, list, "订单明细数据");
    }

    /**
     * 获取订单明细详细信息
     */
    @RequiresPermissions("order:item:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(orderItemInfoService.selectOrderItemInfoById(id));
    }

    /**
     * 新增订单明细
     */
    @RequiresPermissions("order:item:add")
    @Log(title = "订单明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrderItemInfo orderItemInfo)
    {
        return toAjax(orderItemInfoService.insertOrderItemInfo(orderItemInfo));
    }

    /**
     * 修改订单明细
     */
    @RequiresPermissions("order:item:edit")
    @Log(title = "订单明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@RequestBody OrderItemInfo orderItemInfo)
    {
        orderItemInfoService.updateOrderItemInfo(orderItemInfo);
        return R.ok();
    }

    /**
     * 删除订单明细
     */
    @RequiresPermissions("order:item:remove")
    @Log(title = "订单明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orderItemInfoService.deleteOrderItemInfoByIds(ids));
    }
}
