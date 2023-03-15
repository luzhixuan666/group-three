package com.bawei.mall.product.factory;

import com.bawei.common.core.domain.R;
import com.bawei.common.core.utils.StringUtils;
import com.bawei.common.core.web.domain.AjaxResult;
import com.bawei.common.core.web.page.TableDataInfo;
import com.bawei.mall.product.domain.MallProductInfo;
import com.bawei.mall.product.domain.reponse.ProductDetailsResponse;
import com.bawei.mall.product.domain.reponse.ProductInfoResponse;
import com.bawei.mall.product.remote.RemoteProductInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author LuZhixuan
 * @description: 远程调用服务的熔断
 * @Date 2022-10-15 上午 08:42
 */
@Component
public class RemoteProductInfoFallbackFactory implements FallbackFactory<RemoteProductInfo> {

    private final static Logger log = LoggerFactory.getLogger(RemoteProductInfoFallbackFactory.class);

    @Override
    public RemoteProductInfo create (Throwable cause) {
        log.error("商品服务 - 商品信息 - 远程调用异常", cause);
        return new RemoteProductInfo() {
            @Override
            public TableDataInfo syncList (int pageSize, int syncNum, MallProductInfo mallProductInfo) {
                TableDataInfo tableDataInfo = new TableDataInfo();
                tableDataInfo.setMsg(StringUtils.format("远程调用失败，错误信息:[{}]",cause.getMessage()));
                tableDataInfo.setCode(R.FAIL);
                return tableDataInfo;
            }

            @Override
            public R<ProductDetailsResponse> getProductResponse (Long id) {
                return R.fail(StringUtils.format(
                        "获取商品信息[{}]异常：[{}]",id,cause.getMessage()
                ));
            }

            @Override
            public R count (String getType, MallProductInfo mallProductInfo) {
                return R.fail(StringUtils.format("远程调用失败，错误信息:[{}]",cause.getMessage()));
            }

            @Override
            public R<ProductInfoResponse> getResultInfo (Long id) {
                return R.fail(StringUtils.format("远程调用失败，错误信息:[{}]",cause.getMessage()));
            }

            @Override
            public R lockProductStock() {
                return null;
            }
        };
    }
}
