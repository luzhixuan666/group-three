package com.bawei.mall.product.service.impl;

import java.util.List;

import com.bawei.common.core.utils.DateUtils;
import com.bawei.common.core.utils.uuid.IdUtils;
import com.bawei.common.security.utils.SecurityUtils;
import com.bawei.mall.product.domain.model.SkuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bawei.mall.product.mapper.MallProductSkuInfoMapper;
import com.bawei.mall.product.domain.MallProductSkuInfo;
import com.bawei.mall.product.service.IMallProductSkuInfoService;

/**
 * 商品SKUService业务层处理
 *
 * @author Luzhixuan
 * @date 2023-02-30
 */
@Service
public class MallProductSkuInfoServiceImpl implements IMallProductSkuInfoService
{
    @Autowired
    private MallProductSkuInfoMapper mallProductSkuInfoMapper;

    /**
     * 查询商品SKU
     *
     * @param id 商品SKU主键
     * @return 商品SKU
     */
    @Override
    public MallProductSkuInfo selectMallProductSkuInfoById(Long id)
    {
        return mallProductSkuInfoMapper.selectMallProductSkuInfoById(id);
    }

    /**
     * 查询商品SKU列表
     *
     * @param mallProductSkuInfo 商品SKU
     * @return 商品SKU
     */
    @Override
    public List<MallProductSkuInfo> selectMallProductSkuInfoList(MallProductSkuInfo mallProductSkuInfo)
    {
        return mallProductSkuInfoMapper.selectMallProductSkuInfoList(mallProductSkuInfo);
    }

    /**
     * 新增商品SKU
     *
     * @param mallProductSkuInfo 商品SKU
     * @return 结果
     */
    @Override
    public int insertMallProductSkuInfo(MallProductSkuInfo mallProductSkuInfo)
    {
        mallProductSkuInfo.setCreateBy(String.valueOf(SecurityUtils.getUserId()));
        mallProductSkuInfo.setCreateTime(DateUtils.getNowDate());
        return mallProductSkuInfoMapper.insertMallProductSkuInfo(mallProductSkuInfo);
    }

    @Override
    public int batchInsertProductSku (SkuModel skuModel) {
        skuModel.getSkuInfoList().forEach(skuInfo -> {
            skuInfo.setNumber(IdUtils.fastSimpleUUID());
        });
        return mallProductSkuInfoMapper.batchInsertProductSku(skuModel);
    }

    /**
     * 修改商品SKU
     *
     * @param mallProductSkuInfo 商品SKU
     * @return 结果
     */
    @Override
    public int updateMallProductSkuInfo(MallProductSkuInfo mallProductSkuInfo)
    {
        mallProductSkuInfo.setUpdateBy(String.valueOf(SecurityUtils.getUserId()));
        mallProductSkuInfo.setUpdateTime(DateUtils.getNowDate());
        return mallProductSkuInfoMapper.updateMallProductSkuInfo(mallProductSkuInfo);
    }

    /**
     * 批量删除商品SKU
     *
     * @param ids 需要删除的商品SKU主键
     * @return 结果
     */
    @Override
    public int deleteMallProductSkuInfoByIds(Long[] ids)
    {
        return mallProductSkuInfoMapper.deleteMallProductSkuInfoByIds(ids);
    }

    /**
     * 删除商品SKU信息
     *
     * @param id 商品SKU主键
     * @return 结果
     */
    @Override
    public int deleteMallProductSkuInfoById(Long id)
    {
        return mallProductSkuInfoMapper.deleteMallProductSkuInfoById(id);
    }

    @Override
    public void deleteMallProductSkuInfoByProductId (Long productId) {
        mallProductSkuInfoMapper.deleteMallProductSkuInfoByProductId(productId);
    }

    @Override
    public void deleteMallProductSkuInfoByProductIds (Long[] ids) {
        mallProductSkuInfoMapper.deleteMallProductSkuInfoByProductIds(ids);
    }

    /**
     * 扣减库存
     * @param productSkuInfo
     */
    @Override
    public void deductionStock(MallProductSkuInfo productSkuInfo) {
        mallProductSkuInfoMapper.deductionStock(productSkuInfo);
    }
}
