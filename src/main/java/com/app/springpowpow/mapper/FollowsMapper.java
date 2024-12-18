package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.FollowsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowsMapper {

    // 팔로우 추가
    public void insertFollow(FollowsVO followsVO);

    // 팔로우 취소
    public void deleteFollow(FollowsVO followsVO);

    // 특정 유저의 팔로워 리스트 조회
    public List<FollowsVO> selectFollowerList(Long followerMemberId);

    // 특정 유저의 팔로잉 리스트 조회
    public List<FollowsVO> selectFollowingList(Long followingMemberId);

}