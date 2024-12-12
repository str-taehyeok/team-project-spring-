package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.CouponVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CouponMapper {

//    쿠폰 전체 조회
    public List<CouponVO> selectAll();

//    회원 쿠폰 조회
    public List<CouponVO> selectById(Long memberId);

//    쿠폰 조회 (단일)
    public Optional<CouponVO> select(Long id);

//    쿠폰 추가
    public void insert(CouponVO couponVO);

//    쿠폰 수정
    public void update(CouponVO couponVO);

//    쿠폰 삭제
    public void delete(Long id);

//    쿠폰 사용
    public void deleteById(Long memberId);
}
