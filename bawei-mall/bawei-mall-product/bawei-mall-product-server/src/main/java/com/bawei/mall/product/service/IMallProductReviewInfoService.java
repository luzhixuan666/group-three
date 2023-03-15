package com.bawei.mall.product.service;

import java.util.List;
import com.bawei.mall.product.domain.MallProductReviewInfo;

/**
 * 商品评价Service接口
 *
 * @author Luzhixuan
 * @date 2022-09-26
 */
public interface IMallProductReviewInfoService
{
    /**
     * 查询商品评价
     *
     * @param id 商品评价主键
     * @return 商品评价
     */
    public MallProductReviewInfo selectMallProductReviewInfoById(Long id);

    /**
     * 查询商品评价列表
     *
     * @param mallProductReviewInfo 商品评价
     * @return 商品评价集合
     */
    public List<MallProductReviewInfo> selectMallProductReviewInfoList(MallProductReviewInfo mallProductReviewInfo);

    /**
     * 新增商品评价
     *
     * @param mallProductReviewInfo 商品评价
     * @return 结果
     */
    public int insertMallProductReviewInfo(MallProductReviewInfo mallProductReviewInfo);

    /**
     * 修改商品评价
     *
     * @param mallProductReviewInfo 商品评价
     * @return 结果
     */
    public int updateMallProductReviewInfo(MallProductReviewInfo mallProductReviewInfo);

    /**
     * 批量删除商品评价
     *
     * @param ids 需要删除的商品评价主键集合
     * @return 结果
     */
    public int deleteMallProductReviewInfoByIds(Long[] ids);

    /**
     * 删除商品评价信息
     *
     * @param id 商品评价主键
     * @return 结果
     */
    public int deleteMallProductReviewInfoById(Long id);
}
