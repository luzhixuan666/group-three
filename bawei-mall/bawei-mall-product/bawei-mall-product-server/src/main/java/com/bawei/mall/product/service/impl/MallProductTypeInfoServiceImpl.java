package com.bawei.mall.product.service.impl;

import java.util.List;

import com.bawei.common.core.utils.DateUtils;
import com.bawei.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bawei.mall.product.mapper.MallProductTypeInfoMapper;
import com.bawei.mall.product.domain.MallProductTypeInfo;
import com.bawei.mall.product.service.IMallProductTypeInfoService;

/**
 * 商品类型Service业务层处理
 *
 * @author Luzhixuan
 * @date 2023-02-30
 */
@Service
public class MallProductTypeInfoServiceImpl implements IMallProductTypeInfoService
{
    @Autowired
    private MallProductTypeInfoMapper mallProductTypeInfoMapper;

    /**
     * 查询商品类型
     *
     * @param id 商品类型主键
     * @return 商品类型
     */
    @Override
    public MallProductTypeInfo selectMallProductTypeInfoById(Long id)
    {
        return mallProductTypeInfoMapper.selectMallProductTypeInfoById(id);
    }

    /**
     * 查询商品类型列表
     *
     * @param mallProductTypeInfo 商品类型
     * @return 商品类型
     */
    @Override
    public List<MallProductTypeInfo> selectMallProductTypeInfoList(MallProductTypeInfo mallProductTypeInfo)
    {
        List<MallProductTypeInfo> mallProductTypeInfos = mallProductTypeInfoMapper.selectMallProductTypeInfoList(mallProductTypeInfo);
        if (mallProductTypeInfos == null || mallProductTypeInfos.size() == 0){
            return null;
        }
        for (MallProductTypeInfo productTypeInfo : mallProductTypeInfos) {
            MallProductTypeInfo mallProductTypeParam = new MallProductTypeInfo();
            mallProductTypeParam.setParentId(productTypeInfo.getId());
            productTypeInfo.setChildren(this.selectMallProductTypeInfoList(mallProductTypeParam));
        }
        return mallProductTypeInfos;
    }

    /**
     * 新增商品类型
     *
     * @param mallProductTypeInfo 商品类型
     * @return 结果
     */
    @Override
    public int insertMallProductTypeInfo(MallProductTypeInfo mallProductTypeInfo)
    {
        mallProductTypeInfo.setCreateBy(String.valueOf(SecurityUtils.getUserId()));
        mallProductTypeInfo.setCreateTime(DateUtils.getNowDate());
        return mallProductTypeInfoMapper.insertMallProductTypeInfo(mallProductTypeInfo);
    }

    /**
     * 修改商品类型
     *
     * @param mallProductTypeInfo 商品类型
     * @return 结果
     */
    @Override
    public int updateMallProductTypeInfo(MallProductTypeInfo mallProductTypeInfo)
    {
        mallProductTypeInfo.setUpdateBy(String.valueOf(SecurityUtils.getUserId()));
        mallProductTypeInfo.setUpdateTime(DateUtils.getNowDate());
        return mallProductTypeInfoMapper.updateMallProductTypeInfo(mallProductTypeInfo);
    }

    /**
     * 批量删除商品类型
     *
     * @param ids 需要删除的商品类型主键
     * @return 结果
     */
    @Override
    public int deleteMallProductTypeInfoByIds(Long[] ids)
    {
        return mallProductTypeInfoMapper.deleteMallProductTypeInfoByIds(ids);
    }

    /**
     * 删除商品类型信息
     *
     * @param id 商品类型主键
     * @return 结果
     */
    @Override
    public int deleteMallProductTypeInfoById(Long id)
    {
        return mallProductTypeInfoMapper.deleteMallProductTypeInfoById(id);
    }


}
