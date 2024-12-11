package com.app.springpowpow.repository;

import com.app.springpowpow.domain.FollowsVO;
import com.app.springpowpow.mapper.FollowsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FollowsDAO {

    private final FollowsMapper followsMapper;

    // 팔로우 추가
    public void addFollow(FollowsVO followsVO) {
        followsMapper.insertFollow(followsVO);
    }

    // 팔로우 취소
    public void removeFollow(FollowsVO followsVO) {
        followsMapper.deleteFollow(followsVO);
    }

    // 팔로잉 리스트 조회
    public List<FollowsVO> getFollowingList(Long followerMemberId) {
        return followsMapper.selectFollowingList(followerMemberId);
    }

    // 팔로워 리스트 조회
    public List<FollowsVO> getFollowerList(Long followingMemberId) {
        return followsMapper.selectFollowerList(followingMemberId);
    }

    // 팔로우 상태 조회
    public boolean checkFollowState(FollowsVO followsVO) {
        return followsMapper.selectFollowState(followsVO) > 0;
    }
}
