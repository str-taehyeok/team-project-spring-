package com.app.springpowpow.service;

import com.app.springpowpow.domain.FollowsVO;
import com.app.springpowpow.repository.FollowsDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class FollowsServiceImpl implements FollowsService {

    private final FollowsDAO followsDAO;

    // 팔로우 추가
    @Override
    public void addFollow(FollowsVO followsVO) {
        followsDAO.followMember(followsVO);
    }

    // 팔로우 취소
    @Override
    public void removeFollow(FollowsVO followsVO) {
        followsDAO.unfollowMember(followsVO);
    }

    // 팔로워 리스트 조회
    @Override
    public List<FollowsVO> getFollowerList(Long followerMemberId) {
        return followsDAO.findFollowerList(followerMemberId);
    }

    // 팔로잉 리스트 조회
    @Override
    public List<FollowsVO> getFollowingList(Long followingMemberId) {
        return followsDAO.findFollowingList(followingMemberId);
    }
}
