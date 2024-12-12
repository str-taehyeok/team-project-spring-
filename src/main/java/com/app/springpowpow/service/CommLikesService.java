package com.app.springpowpow.service;

import com.app.springpowpow.domain.CommLikesDTO;

import java.util.List;

public interface CommLikesService {

    // 좋아요 추가
    public void addLike(CommLikesDTO commLikesDTO);

    // 좋아요 취소
    public void removeLike(Long memberId, Long postId);

    // 내가 누른 좋아요 게시글 보기
    public List<CommLikesDTO> getLikedPostsByMember(Long memberId);

    // 모든 좋아요 기록 조회
    public List<CommLikesDTO> getAllLikes(Long memberId);
}
