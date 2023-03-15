package com.bawei.mall.product.service.impl;

import java.util.List;

import com.bawei.common.core.utils.DateUtils;
import com.bawei.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bawei.mall.product.mapper.MallProductReviewInfoMapper;
import com.bawei.mall.product.domain.MallProductReviewInfo;
import com.bawei.mall.product.service.IMallProductReviewInfoService;

/**
 * 商品评价Service业务层处理
 *
 * @author Luzhixuan
 * @date 2022-09-26
 */
@Service
public class MallProductReviewInfoServiceImpl implements IMallProductReviewInfoService
{
    @Autowired
    private MallProductReviewInfoMapper mallProductReviewInfoMapper;

    /**
     * 查询商品评价
     *
     * @param id 商品评价主键
     * @return 商品评价
     */
    @Override
    public MallProductReviewInfo selectMallProductReviewInfoById(Long id)
    {
        return mallProductReviewInfoMapper.selectMallProductReviewInfoById(id);
    }

    /**
     * 查询商品评价列表
     *
     * @param mallProductReviewInfo 商品评价
     * @return 商品评价
     */
    @Override
    public List<MallProductReviewInfo> selectMallProductReviewInfoList(MallProductReviewInfo mallProductReviewInfo)
    {
        return mallProductReviewInfoMapper.selectMallProductReviewInfoList(mallProductReviewInfo);
    }

    /**
     * 新增商品评价
     *
     * @param mallProductReviewInfo 商品评价
     * @return 结果
     */
    @Override
    public int insertMallProductReviewInfo(MallProductReviewInfo mallProductReviewInfo)
    {
        mallProductReviewInfo.setCreateBy(String.valueOf(SecurityUtils.getUserId()));
        mallProductReviewInfo.setCreateTime(DateUtils.getNowDate());
        return mallProductReviewInfoMapper.insertMallProductReviewInfo(mallProductReviewInfo);
    }

    /**
     * 修改商品评价
     *
     * @param mallProductReviewInfo 商品评价
     * @return 结果
     */
    @Override
    public int updateMallProductReviewInfo(MallProductReviewInfo mallProductReviewInfo)
    {
        mallProductReviewInfo.setUpdateBy(String.valueOf(SecurityUtils.getUserId()));
        mallProductReviewInfo.setUpdateTime(DateUtils.getNowDate());
        return mallProductReviewInfoMapper.updateMallProductReviewInfo(mallProductReviewInfo);
    }

    /**
     * 批量删除商品评价
     *
     * @param ids 需要删除的商品评价主键
     * @return 结果
     */
    @Override
    public int deleteMallProductReviewInfoByIds(Long[] ids)
    {
        return mallProductReviewInfoMapper.deleteMallProductReviewInfoByIds(ids);
    }

    /**
     * 删除商品评价信息
     *
     * @param id 商品评价主键
     * @return 结果
     */
    @Override
    public int deleteMallProductReviewInfoById(Long id)
    {
        return mallProductReviewInfoMapper.deleteMallProductReviewInfoById(id);
    }
}
