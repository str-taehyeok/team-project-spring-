package com.app.springpowpow.repository;

import com.app.springpowpow.domain.BannerDTO;
import com.app.springpowpow.domain.BannerVO;

import com.app.springpowpow.mapper.BannerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BannerDAO {

    private final BannerMapper bannerMapper;

    //      배너 등록
    public void save(BannerVO bannerVO) {
        bannerMapper.insert(bannerVO);
    }

    //    배너 전체 리스트
    public List<BannerDTO> findAll() {
        return bannerMapper.selectAll();
    }

    //    배너 개별 리스트
    public Optional<BannerDTO> findById(Long id) {
        return bannerMapper.select(id);
    }

    //    배너 수정
    public void update(BannerVO bannerVO) {
        bannerMapper.update(bannerVO);
    }

    //    배너 삭제
    public void delete(Long id) {
        bannerMapper.delete(id);
    }

}
