package com.bawei.mall.pay.service;

import java.util.List;
import com.bawei.mall.pay.domain.PayInfo;

/**
 * 支付记录Service接口
 * 
 * @author Luzhixuan
 * @date 2023-03-08
 */
public interface IPayInfoService 
{
    /**
     * 查询支付记录
     * 
     * @param id 支付记录主键
     * @return 支付记录
     */
    public PayInfo selectPayInfoById(Long id);

    /**
     * 查询支付记录列表
     * 
     * @param payInfo 支付记录
     * @return 支付记录集合
     */
    public List<PayInfo> selectPayInfoList(PayInfo payInfo);

    /**
     * 新增支付记录
     * 
     * @param payInfo 支付记录
     * @return 结果
     */
    public int insertPayInfo(PayInfo payInfo);

    /**
     * 修改支付记录
     * 
     * @param payInfo 支付记录
     * @return 结果
     */
    public int updatePayInfo(PayInfo payInfo);

    /**
     * 批量删除支付记录
     * 
     * @param ids 需要删除的支付记录主键集合
     * @return 结果
     */
    public int deletePayInfoByIds(Long[] ids);

    /**
     * 删除支付记录信息
     * 
     * @param id 支付记录主键
     * @return 结果
     */
    public int deletePayInfoById(Long id);
}
