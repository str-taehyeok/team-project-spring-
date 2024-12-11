package com.app.springpowpow.repository;

import com.app.springpowpow.domain.CommLikesDTO;
import com.app.springpowpow.domain.PostVO;
import com.app.springpowpow.mapper.CommLikesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommLikesDAO {

    private final CommLikesMapper commLikesMapper;

    // 좋아요 추가
    public void addLike(CommLikesDTO commLikesDTO) {
        commLikesMapper.insertLike(commLikesDTO);
    }

    // 좋아요 취소
    public void removeLike(CommLikesDTO commLikesDTO) {
        commLikesMapper.deleteLike(commLikesDTO);
    }

    // 내가 누른 좋아요 게시글 보기
    public List<PostVO> getLikedPostsByMember(Long memberId) {
        return commLikesMapper.selectLikedPosts(memberId);
    }

    // 모든 좋아요 기록 조회
    public List<CommLikesDTO> getAllLikes() {
        return commLikesMapper.selectAllLikes();
    }

}
