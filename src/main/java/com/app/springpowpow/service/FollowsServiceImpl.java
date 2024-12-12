package com.app.springpowpow.service;

import com.app.springpowpow.domain.FollowsVO;
import com.app.springpowpow.mapper.FollowsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class FollowsServiceImpl implements FollowsService {

    private final FollowsMapper followsMapper;

    @Override
    public void addFollow(FollowsVO followsVO) {
        followsMapper.insertFollow(followsVO);
    }

    @Override
    public void removeFollow(FollowsVO followsVO) {
        followsMapper.deleteFollow(followsVO);
    }

    @Override
    public List<FollowsVO> getFollowingList(Long memberId) {
        return followsMapper.selectFollowingList(memberId);
    }

    @Override
    public List<FollowsVO> getFollowerList(Long memberId) {
        return followsMapper.selectFollowerList(memberId);
    }

    @Override
    public boolean isFollowing(FollowsVO followsVO) {
        return followsMapper.selectFollowState(followsVO) > 0;
    }
}
