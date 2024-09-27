package com.yefeng.service;

/**
 * @Author: 叶枫
 * @Date: 2024/09/27/16:30
 * @Description:
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.yefeng.model.dto.coupon.CouponFormDTO;
import com.yefeng.model.entity.Coupons;


public interface CouponService extends IService<Coupons> {
    void saveCoupon(CouponFormDTO couponFormDTO);
}
