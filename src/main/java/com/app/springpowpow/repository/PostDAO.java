package com.app.springpowpow.repository;

import com.app.springpowpow.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostDAO {

    private final PostMapper postMapper;





//    회원탈퇴시 모든 게시글 삭제
    public void removeAll(Long memberId){
        postMapper.deleteAll(memberId);
    }


}
