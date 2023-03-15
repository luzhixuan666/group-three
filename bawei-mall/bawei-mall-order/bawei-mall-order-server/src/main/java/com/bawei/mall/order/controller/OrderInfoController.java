package com.bawei.mall.order.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.bawei.common.core.domain.R;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bawei.common.log.annotation.Log;
import com.bawei.common.log.enums.BusinessType;
import com.bawei.common.security.annotation.RequiresPermissions;
import com.bawei.mall.order.domain.OrderInfo;
import com.bawei.mall.order.service.IOrderInfoService;
import com.bawei.common.core.web.controller.BaseController;
import com.bawei.common.core.web.domain.AjaxResult;
import com.bawei.common.core.utils.poi.ExcelUtil;
import com.bawei.common.core.web.page.TableDataInfo;

/**
 * 订单Controller
 *
 * @author LuZhiXuan
 * @date 2023-02-09
 */
@RestController
@RequestMapping("/info")
public class OrderInfoController extends BaseController
{
    @Autowired
    private IOrderInfoService orderInfoService;

    @GetMapping("/getTokenInfo/{uid}")
    public R getTokenInfo(@PathVariable("uid") Long uid) {
        String token = orderInfoService.generateToken(uid);
        return R.ok(token);
    }

    /**
     * 查询订单列表
     */
    @RequiresPermissions("order:info:list")
    @GetMapping("/list")
    public TableDataInfo list(OrderInfo orderInfo)
    {
        startPage();
        List<OrderInfo> list = orderInfoService.selectOrderInfoList(orderInfo);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @RequiresPermissions("order:info:export")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrderInfo orderInfo)
    {
        List<OrderInfo> list = orderInfoService.selectOrderInfoList(orderInfo);
        ExcelUtil<OrderInfo> util = new ExcelUtil<OrderInfo>(OrderInfo.class);
        util.exportExcel(response, list, "订单数据");
    }

    /**
     * 获取订单详细信息
     */
    @RequiresPermissions("order:info:query")
    @GetMapping(value = "/{id}")
    public R<OrderInfo> getInfo(@PathVariable("id") Long id)
    {
        OrderInfo orderInfo = orderInfoService.selectOrderInfoById(id);
        return R.ok(orderInfo);
    }


    @GetMapping("/findbyNumber/{number}")
    public R<OrderInfo> getInfoNumber(@PathVariable("number") String number)
    {
        OrderInfo orderInfo = orderInfoService.selectOrderInfoByNumber(number);
        return R.ok(orderInfo);
    }

    /**
     * 新增订单
     */
    @RequiresPermissions("order:info:add")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping()
    public R add() {
        orderInfoService.generateOrderInfo();
        return R.ok();
    }

    /**
     * 修改订单
     */
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@RequestBody OrderInfo orderInfo)
    {
        orderInfoService.updateOrderInfo(orderInfo);
        return R.ok();
    }

    /**
     * 删除订单
     */
    @RequiresPermissions("order:info:remove")
    @Log(title = "订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orderInfoService.deleteOrderInfoByIds(ids));
    }
}
