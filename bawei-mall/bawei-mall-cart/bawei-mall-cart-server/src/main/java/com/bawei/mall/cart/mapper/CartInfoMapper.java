package com.bawei.mall.cart.mapper;

import java.util.List;
import com.bawei.mall.cart.domain.CartInfo;
import com.bawei.mall.cart.domain.model.RemoveCarProductModel;
import com.bawei.mall.cart.domain.request.RemoveCarProductRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 购物车Mapper接口
 *
 * @author Luzhixuan
 * @date 2023-01-30
 */

public interface CartInfoMapper
{
    /**
     * 查询购物车
     *
     * @param id 购物车主键
     * @return 购物车
     */
    public CartInfo selectCartInfoById(Long id);

    /**
     * 查询购物车列表
     *
     * @param cartInfo 购物车
     * @return 购物车集合
     */
    public List<CartInfo> selectCartInfoList(CartInfo cartInfo);

    /**
     * 新增购物车
     *
     * @param cartInfo 购物车
     * @return 结果
     */
    public int insertCartInfo(CartInfo cartInfo);

    /**
     * 修改购物车
     *
     * @param cartInfo 购物车
     * @return 结果
     */
    public int updateCartInfo(CartInfo cartInfo);

    /**
     * 删除购物车
     *
     * @param
     * @return 结果
     */
    public int deleteCartInfoById(Long id);

    /**
     * 批量删除购物车
     *
     * @param
     * @return 结果
     */
    public int deleteCartInfoByIds(@Param("ids") List<Long> ids);
}
