package com.yefeng.controller;

import com.yefeng.model.dto.coupon.CouponFormDTO;
import com.yefeng.service.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 叶枫
 * @Date: 2024/09/27/16:22
 * @Description:
 */
@Api(tags = "优惠卷相关接口")
@RestController()
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;

    @ApiOperation("新增优惠券接口")
    @PostMapping
    public void saveCoupon(@RequestBody @Validated CouponFormDTO couponFormDTO) {
        couponService.saveCoupon(couponFormDTO);
    }
}
