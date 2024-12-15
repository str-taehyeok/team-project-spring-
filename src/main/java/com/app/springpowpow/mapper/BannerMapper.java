package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.BannerDTO;
import com.app.springpowpow.domain.BannerVO;
import com.app.springpowpow.domain.NoticeDTO;
import com.app.springpowpow.domain.NoticeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BannerMapper {
    //    배너 작성
    public void insert(BannerVO bannerVO);

    //    배너 전체 리스트
    public List<BannerDTO> selectAll();

    //    배너 개별 리스트
    public Optional<BannerDTO> select(Long id);

    //    배너 수정
    public void update(BannerVO bannerVO);

    //    배너 삭제
    public void delete(Long id);
}
