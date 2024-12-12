package com.app.springpowpow;

import com.app.springpowpow.domain.CouponVO;
import com.app.springpowpow.mapper.CouponMapper;
import com.app.springpowpow.repository.CouponDAO;
import com.app.springpowpow.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class CouponTests {

    @Autowired
    private CouponService couponService;
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private CouponDAO couponDAO;

    @Test
    public void CouponAddTest() {

        CouponVO couponVO = new CouponVO();

        couponVO.setId(105L);
        couponVO.setMemberId(1L);
        couponVO.setCouponTitle("이것은 쿠폰제목4");
        couponVO.setCouponCategory("장난감");
        couponVO.setCouponCategoryAnimal("고양이");
        couponVO.setCouponCode("423456789ABCDEFG");
        couponVO.setCouponStart("2024-12-12");
        couponVO.setCouponEnd("2024-12-29");
        couponVO.setCouponContent("이것은 쿠폰내용4");
        couponVO.setCouponQuantity(44);
        couponVO.setCouponDiscountRate(20);

//        couponMapper.insert(couponVO);
//        couponDAO.save(couponVO);
        couponService.registerCoupon(couponVO);
    }

    @Test
    public void CouponSelectTest() {

        couponService.getAllCoupons()
                .stream()
                .map(CouponVO::toString)
                .forEach(log::info);
    }

    @Test
    public void CouponSelectByIdTest() {
        couponService.getCouponById(26L).ifPresent(coupon -> {
            log.info("{}",coupon.toString());
        });
    }

    @Test
    public void CouponSelectByMemberIdTest() {
        couponService.getCouponByMemberId(1L).stream()
                .map(CouponVO::toString)
                .forEach(log::info);
    }

    @Test
    public void CouponUpdateTest() {
        CouponVO couponVO = new CouponVO();

        couponVO.setId(26L);
        couponVO.setMemberId(1L);
        couponVO.setCouponTitle("이것은 수정1한 쿠폰제목5");
        couponVO.setCouponCategory("장난감");
        couponVO.setCouponCategoryAnimal("고양이");
        couponVO.setCouponCode("923456789ABCDEFG");
        couponVO.setCouponStart("2024-12-12");
        couponVO.setCouponEnd("2024-12-29");
        couponVO.setCouponContent("이것은 수정1한 쿠폰내용5");
        couponVO.setCouponQuantity(43);
        couponVO.setCouponDiscountRate(10);

        couponService.modifyCoupon(couponVO);
    }

    @Test
    public void CouponDeleteTest() {
        couponService.deleteCoupon(26L);
    }
}
