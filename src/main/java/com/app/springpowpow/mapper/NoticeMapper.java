package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.NoticeDTO;
import com.app.springpowpow.domain.NoticeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface NoticeMapper {

//    공지사항 작성
    public void insert(NoticeVO  noticeVO);

//    공지사항 전체 리스트
    public List<NoticeDTO> selectAll();

//    공지사항 개별 리스트
    public Optional<NoticeDTO> select(Long id);

//    공지사항 수정
    public void update(NoticeVO noticeVO);

//    공지사항 등록
    public void delete(Long id);

}
