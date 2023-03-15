package com.bawei.mall.product.controller;

import java.util.List;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.bawei.common.core.domain.R;
import com.bawei.common.core.utils.KeyUtils;
import com.bawei.common.core.utils.StringUtils;
import com.bawei.common.lock.ression.service.RedissonService;
import com.bawei.common.redis.service.RedisService;
import com.bawei.common.security.utils.SecurityUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
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
import com.bawei.mall.product.domain.MallProductTypeInfo;
import com.bawei.mall.product.service.IMallProductTypeInfoService;
import com.bawei.common.core.web.controller.BaseController;
import com.bawei.common.core.web.domain.AjaxResult;
import com.bawei.common.core.utils.poi.ExcelUtil;

/**
 * 商品类型Controller
 *
 * @author Luzhixuan
 * @date 2023-02-30
 */
@RestController
@RequestMapping("/type")
public class MallProductTypeInfoController extends BaseController
{
    @Autowired
    private IMallProductTypeInfoService mallProductTypeInfoService;
    @Autowired
    private RedisService redisService;

    @Autowired
    private RedissonService redissonService;

    @Autowired
    private RedissonClient redissonClient;

    private static String KEY_TYPE="type:{}:info";
    /**
     * 查询商品类型列表
     */
    @RequiresPermissions("product:type:list")
    @GetMapping("/list")
    @Retryable(value = Exception.class, maxAttempts = 3,
            backoff= @Backoff(value = 1000, maxDelay = 1000, multiplier = 1.2))
    public R redislist(MallProductTypeInfo mallProductTypeInfo)  {
        Long userId = SecurityUtils.getUserId();
        String typeKey = StringUtils.format(KEY_TYPE, userId);
        //从缓存中获取数据
        String listJosn= String.valueOf(redisService.redisTemplate.opsForValue().get(typeKey));
        List<MallProductTypeInfo> list = JSONObject.parseArray(listJosn, MallProductTypeInfo.class);
        if (list!=null){
            return R.ok(list);
        }
        RLock lock=null;
        List<MallProductTypeInfo> typelist = null;
        //进行加锁操作
        try {
            lock = redissonService.lock(typeKey,SecurityUtils.getToken(),3000,3,3000);
            //获取锁并设定等待时间和持有时间，以避免出现死锁
            boolean locked = lock.tryLock(5, 30, TimeUnit.SECONDS);
                if (locked){
                    //再次判断缓存中是否已经存在数据
                    listJosn= String.valueOf(redisService.redisTemplate.opsForValue().get(typeKey));
                    list = JSONObject.parseArray(listJosn, MallProductTypeInfo.class);
                    if (list!=null){
                        return R.ok(list);
                    }
                    //缓存中仍然不存在，则从数据库中查询数据
                    typelist = mallProductTypeInfoService.selectMallProductTypeInfoList(mallProductTypeInfo);
                    if (typelist!=null){
                        redisService.redisTemplate.opsForValue().set(typeKey,JSONObject.toJSONString(typelist),30,TimeUnit.SECONDS);
                        // 设置缓存数据的过期时间并添加随机值,使得缓存的数据在不同的时间失效,防雪崩问题
                        int random = new Random().nextInt(60 * 10);
                        redissonClient.getBucket(typeKey).expire(60 * 30 + random, TimeUnit.SECONDS);
                    }
                    //如果数据已经不存在，缓存中添加空对象或者默认值，以避免重复查询数据库，防止穿透
                    else {
                        redisService.redisTemplate.opsForValue().set(typeKey,"信息丢失",10,TimeUnit.SECONDS);
                    }
                } else {
                    //获取锁失败,等待分布式锁的线程直接返空数据
                    listJosn= String.valueOf(redisService.redisTemplate.opsForValue().get(typeKey));
                    if (listJosn!=null){
                        list = JSONObject.parseArray(listJosn, MallProductTypeInfo.class);
                        return R.ok(list);
                    }
                }
        } catch (InterruptedException e) {
            //加锁失败，重新设置中断标志
            Thread.currentThread().interrupt();
            //释放锁
            if (lock!=null&&lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
        //从新列表中获取
        return R.ok(typelist);
    }











//    public AjaxResult list(MallProductTypeInfo mallProductTypeInfo)
//    {
//        List<MallProductTypeInfo> list = mallProductTypeInfoService.selectMallProductTypeInfoList(mallProductTypeInfo);
//        return AjaxResult.success(list);
//    }
    @RequiresPermissions("product:type:list")
    @GetMapping("/tree")
    public AjaxResult tree()
    {
        MallProductTypeInfo mallProductTypeInfo = new MallProductTypeInfo();
        mallProductTypeInfo.setParentId(0L);
        List<MallProductTypeInfo> list = mallProductTypeInfoService.selectMallProductTypeInfoList(mallProductTypeInfo);
        return AjaxResult.success(list);
    }



    /**
     * 导出商品类型列表
     */
    @RequiresPermissions("product:type:export")
    @Log(title = "商品类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MallProductTypeInfo mallProductTypeInfo)
    {
        List<MallProductTypeInfo> list = mallProductTypeInfoService.selectMallProductTypeInfoList(mallProductTypeInfo);
        ExcelUtil<MallProductTypeInfo> util = new ExcelUtil<MallProductTypeInfo>(MallProductTypeInfo.class);
        util.exportExcel(response, list, "商品类型数据");
    }

    /**
     * 获取商品类型详细信息
     */
    @RequiresPermissions("product:type:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(mallProductTypeInfoService.selectMallProductTypeInfoById(id));
    }

    /**
     * 新增商品类型
     */
    @RequiresPermissions("product:type:add")
    @Log(title = "商品类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MallProductTypeInfo mallProductTypeInfo)
    {
        return toAjax(mallProductTypeInfoService.insertMallProductTypeInfo(mallProductTypeInfo));
    }

    /**
     * 修改商品类型
     */
    @RequiresPermissions("product:type:edit")
    @Log(title = "商品类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MallProductTypeInfo mallProductTypeInfo)
    {
        return toAjax(mallProductTypeInfoService.updateMallProductTypeInfo(mallProductTypeInfo));
    }

    /**
     * 删除商品类型
     */
    @RequiresPermissions("product:type:remove")
    @Log(title = "商品类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mallProductTypeInfoService.deleteMallProductTypeInfoByIds(ids));
    }
}
