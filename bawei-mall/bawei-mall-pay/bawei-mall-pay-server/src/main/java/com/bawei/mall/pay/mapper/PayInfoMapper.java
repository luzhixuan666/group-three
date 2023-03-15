package com.bawei.mall.pay.mapper;

import java.util.List;
import com.bawei.mall.pay.domain.PayInfo;

/**
 * 支付记录Mapper接口
 * 
 * @author Luzhixuan
 * @date 2023-03-08
 */
public interface PayInfoMapper 
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
     * 删除支付记录
     * 
     * @param id 支付记录主键
     * @return 结果
     */
    public int deletePayInfoById(Long id);

    /**
     * 批量删除支付记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePayInfoByIds(Long[] ids);
}
