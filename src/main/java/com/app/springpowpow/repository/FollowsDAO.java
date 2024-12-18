package com.app.springpowpow.repository;

import com.app.springpowpow.domain.FollowsDTO;
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
    public void followMember(FollowsVO followsVO) {
        followsMapper.insertFollow(followsVO);
    }

    // 팔로우 취소
    public void unfollowMember(FollowsVO followsVO) {
        followsMapper.deleteFollow(followsVO);
    }

    // 특정 유저의 팔로워 리스트 조회
    public List<FollowsDTO> findFollowerList(Long followerMemberId) {
        return followsMapper.selectFollowerList(followerMemberId);
    }

    // 특정 유저의 팔로잉 리스트 조회
    public List<FollowsDTO> findFollowingList(Long followingMemberId) {
        return followsMapper.selectFollowingList(followingMemberId);
    }
}
