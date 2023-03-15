package com.bawei.mall.pay.service.impl;

import java.util.List;
import com.bawei.common.core.utils.DateUtils;
import com.bawei.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bawei.mall.pay.mapper.PayInfoMapper;
import com.bawei.mall.pay.domain.PayInfo;
import com.bawei.mall.pay.service.IPayInfoService;

/**
 * 支付记录Service业务层处理
 *
 * @author Luzhixuan
 * @date 2023-03-08
 */
@Service
public class PayInfoServiceImpl implements IPayInfoService
{
    @Autowired
    private PayInfoMapper payInfoMapper;

    /**
     * 查询支付记录
     *
     * @param id 支付记录主键
     * @return 支付记录
     */
    @Override
    public PayInfo selectPayInfoById(Long id)
    {
        return payInfoMapper.selectPayInfoById(id);
    }

    /**
     * 查询支付记录列表
     *
     * @param payInfo 支付记录
     * @return 支付记录
     */
    @Override
    public List<PayInfo> selectPayInfoList(PayInfo payInfo)
    {
        return payInfoMapper.selectPayInfoList(payInfo);
    }

    /**
     * 新增支付记录
     *
     * @param payInfo 支付记录
     * @return 结果
     */
    @Override
    public int insertPayInfo(PayInfo payInfo)
    {
        payInfo.setCreateTime(DateUtils.getNowDate());
        payInfo.setCreateBy(String.valueOf(SecurityUtils.getUserId()));
        return payInfoMapper.insertPayInfo(payInfo);
    }

    /**
     * 修改支付记录
     *
     * @param payInfo 支付记录
     * @return 结果
     */
    @Override
    public int updatePayInfo(PayInfo payInfo)
    {
        return payInfoMapper.updatePayInfo(payInfo);
    }

    /**
     * 批量删除支付记录
     *
     * @param ids 需要删除的支付记录主键
     * @return 结果
     */
    @Override
    public int deletePayInfoByIds(Long[] ids)
    {
        return payInfoMapper.deletePayInfoByIds(ids);
    }

    /**
     * 删除支付记录信息
     *
     * @param id 支付记录主键
     * @return 结果
     */
    @Override
    public int deletePayInfoById(Long id)
    {
        return payInfoMapper.deletePayInfoById(id);
    }
}
