package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.CommLikesDTO;
import com.app.springpowpow.domain.CommLikesVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommLikesMapper {

    // 좋아요 추가
    public void insertLike(CommLikesVO commLikesVO);

    // 좋아요 취소
    public void deleteLike(CommLikesVO commLikesVO);

    // 내가 누른 좋아요 게시글 보기
    public List<CommLikesDTO> selectLikedPosts(Long memberId);

    // 모든 좋아요 기록 조회 (특정 회원의 좋아요 기록 조회)
    public List<CommLikesDTO> selectAllLikes(Long memberId);
}
