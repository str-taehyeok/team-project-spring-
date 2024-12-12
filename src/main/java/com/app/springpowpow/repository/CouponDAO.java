package com.app.springpowpow.repository;

import com.app.springpowpow.domain.CouponVO;
import com.app.springpowpow.mapper.CouponMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CouponDAO {

    private final CouponMapper couponMapper;

    public List<CouponVO> findAll() {
        return couponMapper.selectAll();
    }

    public List<CouponVO> findById(Long memberId) {
        return couponMapper.selectById(memberId);
    }

    public Optional<CouponVO> find(Long id) {
        return couponMapper.select(id);
    }

    public void save(CouponVO couponVO) {
        couponMapper.insert(couponVO);
    }

    public void update(CouponVO couponVO) {
        couponMapper.update(couponVO);
    }

    public void delete(Long id) {
        couponMapper.delete(id);
    }

    public void deleteById(Long memberId) {
        couponMapper.deleteById(memberId);
    }
}
