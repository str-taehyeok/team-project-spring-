package com.app.springpowpow.service;

import com.app.springpowpow.domain.CommLikesDTO;
import com.app.springpowpow.repository.CommLikesDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class CommLikesServiceImpl implements CommLikesService {

    @Autowired
    private final CommLikesDAO commLikesDAO;

    // 좋아요 추가
    @Override
    public void addLike(CommLikesDTO commLikesDTO) {
        commLikesDAO.addLike(commLikesDTO);
    }

    // 좋아요 취소
    @Override
    public void removeLike(Long memberId, Long postId) {
        commLikesDAO.removeLike(memberId, postId);
    }

    // 내가 누른 좋아요 게시글 보기
    @Override
    public List<CommLikesDTO> getLikedPostsByMember(Long memberId) {
        return commLikesDAO.getLikedPostsByMember(memberId);
    }

    // 모든 좋아요 기록 조회
    @Override
    public List<CommLikesDTO> getAllLikes(Long memberId) {
        return commLikesDAO.getAllLikes(memberId);
    }
}
