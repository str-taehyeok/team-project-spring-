package com.app.springpowpow.service;


import com.app.springpowpow.domain.CouponVO;

import java.util.List;
import java.util.Optional;

public interface CouponService {

    public List<CouponVO> getAllCoupons();

    public Optional<CouponVO> getCouponById(Long id);

    public List<CouponVO> getCouponByMemberId(Long memberId);

    public void registerCoupon(CouponVO couponVO);

    public void modifyCoupon(CouponVO couponVO);

    public void deleteCoupon(Long id);

    public void useCouponByMemberId(Long memberId);
}
