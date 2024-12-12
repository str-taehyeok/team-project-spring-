package com.app.springpowpow.service;

import com.app.springpowpow.domain.CouponVO;
import com.app.springpowpow.repository.CouponDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CouponServiceImpl implements CouponService {

    private final CouponDAO couponDAO;

    @Override
    public List<CouponVO> getAllCoupons() {
        return couponDAO.findAll();
    }

    @Override
    public Optional<CouponVO> getCouponById(Long id) {
        return couponDAO.find(id);
    }

    @Override
    public List<CouponVO> getCouponByMemberId(Long memberId) {
        return couponDAO.findById(memberId);
    }

    @Override
    public void registerCoupon(CouponVO couponVO) {
        couponDAO.save(couponVO);
    }

    @Override
    public void modifyCoupon(CouponVO couponVO) {
        couponDAO.update(couponVO);
    }

    @Override
    public void deleteCoupon(Long id) {
        couponDAO.delete(id);
    }

    @Override
    public void useCouponByMemberId(Long memberId) {
        couponDAO.deleteById(memberId);
    }
}
