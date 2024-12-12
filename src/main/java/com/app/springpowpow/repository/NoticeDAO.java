package com.app.springpowpow.repository;

import com.app.springpowpow.domain.NoticeDTO;
import com.app.springpowpow.domain.NoticeVO;
import com.app.springpowpow.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NoticeDAO {

    private final NoticeMapper noticeMapper;

//      공지사항 등록
    public void save(NoticeVO noticeVO) {
        noticeMapper.insert(noticeVO);
    }

//    공지사항 전체 리스트
    public List<NoticeDTO> findAll() {
        return noticeMapper.selectAll();
    }

//    공지사항 개별 리스트
    public Optional<NoticeDTO> findById(Long id) {
        return noticeMapper.select(id);
    }

//    공지사항 수정
    public void update(NoticeVO noticeVO) {
        noticeMapper.update(noticeVO);
    }

//    공지사항 삭제
    public void delete(Long id) {
        noticeMapper.delete(id);
    }

}
