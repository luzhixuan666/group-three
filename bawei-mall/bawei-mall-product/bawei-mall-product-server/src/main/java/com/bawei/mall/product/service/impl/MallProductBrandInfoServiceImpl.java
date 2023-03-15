package com.bawei.mall.product.service.impl;

import java.util.List;
import com.bawei.common.core.utils.DateUtils;
import com.bawei.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bawei.mall.product.mapper.MallProductBrandInfoMapper;
import com.bawei.mall.product.domain.MallProductBrandInfo;
import com.bawei.mall.product.service.IMallProductBrandInfoService;

/**
 * 商品品牌Service业务层处理
 *
 * @author Luzhixuan
 * @date 2022-09-15
 */
@Service
public class MallProductBrandInfoServiceImpl implements IMallProductBrandInfoService
{
    @Autowired
    private MallProductBrandInfoMapper mallProductBrandInfoMapper;

    /**
     * 查询商品品牌
     *
     * @param id 商品品牌主键
     * @return 商品品牌
     */
    @Override
    public MallProductBrandInfo selectMallProductBrandInfoById(Long id)
    {
        return mallProductBrandInfoMapper.selectMallProductBrandInfoById(id);
    }

    /**
     * 查询商品品牌列表
     *
     * @param mallProductBrandInfo 商品品牌
     * @return 商品品牌
     */
    @Override
    public List<MallProductBrandInfo> selectMallProductBrandInfoList(MallProductBrandInfo mallProductBrandInfo)
    {
        return mallProductBrandInfoMapper.selectMallProductBrandInfoList(mallProductBrandInfo);
    }

    /**
     * 新增商品品牌
     *
     * @param mallProductBrandInfo 商品品牌
     * @return 结果
     */
    @Override
    public int insertMallProductBrandInfo(MallProductBrandInfo mallProductBrandInfo)
    {
        mallProductBrandInfo.setCreateBy(String.valueOf(SecurityUtils.getUserId()));
        mallProductBrandInfo.setCreateTime(DateUtils.getNowDate());
        return mallProductBrandInfoMapper.insertMallProductBrandInfo(mallProductBrandInfo);
    }

    /**
     * 修改商品品牌
     *
     * @param mallProductBrandInfo 商品品牌
     * @return 结果
     */
    @Override
    public int updateMallProductBrandInfo(MallProductBrandInfo mallProductBrandInfo)
    {
        mallProductBrandInfo.setUpdateTime(DateUtils.getNowDate());
        return mallProductBrandInfoMapper.updateMallProductBrandInfo(mallProductBrandInfo);
    }

    /**
     * 批量删除商品品牌
     *
     * @param ids 需要删除的商品品牌主键
     * @return 结果
     */
    @Override
    public int deleteMallProductBrandInfoByIds(Long[] ids)
    {
        return mallProductBrandInfoMapper.deleteMallProductBrandInfoByIds(ids);
    }

    /**
     * 删除商品品牌信息
     *
     * @param id 商品品牌主键
     * @return 结果
     */
    @Override
    public int deleteMallProductBrandInfoById(Long id)
    {
        return mallProductBrandInfoMapper.deleteMallProductBrandInfoById(id);
    }
}
