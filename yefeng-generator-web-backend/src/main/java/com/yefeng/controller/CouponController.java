package com.yefeng.controller;

import com.yefeng.model.dto.coupon.CouponFormDTO;
import com.yefeng.model.vo.CouponDetailVO;
import com.yefeng.service.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @ApiOperation("修改优惠券接口")
    @PutMapping("{id}")
    public void updateById(@RequestBody @Valid CouponFormDTO dto, @PathVariable("id") Long id){
        couponService.updateById(dto, id);
    }

    @ApiOperation("删除优惠券接口")
    @DeleteMapping("{id}")
    public void deleteById(@ApiParam("优惠券id") @PathVariable("id") Long id){
        couponService.deleteById(id);
    }

    @ApiOperation("查询优惠券接口")
    @GetMapping("{id}")
    public CouponDetailVO queryById(@ApiParam("优惠券id") @PathVariable("id") Long id){
        return couponService.queryById(id);
    }
}
