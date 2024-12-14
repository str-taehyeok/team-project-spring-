package com.app.springpowpow.service;

import com.app.springpowpow.repository.PostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class PostServiceImpl implements PostService {

    private final PostDAO postDAO;



//  회원탈퇴시 삭제
    @Override
    public void removeAll(Long memberId) {
        postDAO.removeAll(memberId);
    }
}
