package com.yefeng.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yefeng.common.ErrorCode;
import com.yefeng.exception.BusinessException;
import com.yefeng.mapper.CouponMapper;
import com.yefeng.model.dto.coupon.CouponFormDTO;
import com.yefeng.model.entity.CouponScope;
import com.yefeng.model.entity.Coupons;
import com.yefeng.service.CouponScopeService;
import com.yefeng.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 叶枫
 * @Date: 2024/09/27/16:30
 * @Description:
 */

@Service
@RequiredArgsConstructor
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupons> implements CouponService {
    private final CouponScopeService scopeService;

    @Override
    public void saveCoupon(CouponFormDTO couponFormDTO) {
        Coupons coupons = BeanUtil.copyProperties(couponFormDTO, Coupons.class);
        this.save(coupons);
        if (!couponFormDTO.getSpecific()) {
            return;
        }
        List<Long> scopes = couponFormDTO.getScopes();
        if (CollUtil.isEmpty(scopes)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "分类id不能为空");
        }
        List<CouponScope> list = scopes.stream().map(item -> new CouponScope()
                .setCouponId(coupons.getId())
                .setBizId(item)
                .setType(1)
        ).collect(Collectors.toList());
        scopeService.saveBatch(list);
    }
}
