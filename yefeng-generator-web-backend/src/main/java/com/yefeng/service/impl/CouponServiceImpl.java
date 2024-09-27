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

    @Override
    public void updateById(CouponFormDTO dto, Long id) {
        //1.校验参数
        Long dtoId = dto.getId();
        //如果dto的id和路径id都存在但id不一致，或者都不存在，则抛出异常
        if((dtoId!=null && id!=null && !dtoId.equals(id)) || (dtoId==null&&id==null)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "分类id不能为空");
        }
        //2.更新优惠券基本信息
        Coupons coupon = BeanUtil.copyProperties(dto, Coupons.class);
        //只更新状态为1的优惠券基本信息，如果失败则是状态已修改
        boolean update = lambdaUpdate().eq(Coupons::getStatus, 1).update(coupon);
        //基本信息更新失败则无需更新优惠券范围信息
        if(!update){
            return;
        }
        //3.更新优惠券范围信息
        List<Long> scopeIds = dto.getScopes();
        //3.1只要是优惠券状态不为1，或者优惠券范围为空，则不更新优惠券范围信息
        //3.2个人写法是先删除优惠券范围信息，再重新插入
        List<Long> ids = scopeService.lambdaQuery().select(CouponScope::getId).eq(CouponScope::getCouponId, dto.getId()).list()
                .stream().map(CouponScope::getId).collect(Collectors.toList());
        scopeService.removeByIds(ids);
        //3.3删除成功后，并且有范围再插入
        if(CollUtil.isNotEmpty(scopeIds)){
            List<CouponScope> lis = scopeIds.stream().map(i -> new CouponScope().setCouponId(dto.getId()).setType(1).setBizId(i)).collect(Collectors.toList());
            scopeService.saveBatch(lis);
        }
    }
}
