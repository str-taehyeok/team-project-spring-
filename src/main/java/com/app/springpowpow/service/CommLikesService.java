package com.app.springpowpow.service;

import com.app.springpowpow.domain.CommLikesDTO;
import com.app.springpowpow.domain.PostVO;

import java.util.List;
import java.util.Optional;

public interface CommLikesService {

    // 좋아요 추가
    void addLike(CommLikesDTO commLikesDTO);

    // 좋아요 취소
    void removeLike(CommLikesDTO commLikesDTO);

    // 내가 누른 좋아요 게시글 보기
    List<PostVO> getLikedPostsByMember(Long memberId);

    // 모든 좋아요 기록 조회
    List<CommLikesDTO> getAllLikes();
}
