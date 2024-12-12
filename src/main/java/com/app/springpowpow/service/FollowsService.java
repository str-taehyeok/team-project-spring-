package com.app.springpowpow.service;

import com.app.springpowpow.domain.FollowsVO;

import java.util.List;

public interface FollowsService {

    // 팔로우 추가
    public void addFollow(FollowsVO followsVO);

    // 팔로우 취소
    public void removeFollow(FollowsVO followsVO);

    // 특정 유저의 팔로잉 리스트 조회
    public List<FollowsVO> getFollowingList(Long memberId);

    // 특정 유저의 팔로워 리스트 조회
    public List<FollowsVO> getFollowerList(Long memberId);

    // 팔로우 상태 조회 (특정 유저가 다른 유저를 팔로우 중인지 아닌지 확인)
    public boolean isFollowing(FollowsVO followsVO);

}
