package com.app.springpowpow.post;

import com.app.springpowpow.domain.PostDTO;
import com.app.springpowpow.mapper.PostMapper;
import com.app.springpowpow.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PostMapperTests {

    @Autowired
    private PostService postService;
    @Autowired
    private PostMapper postMapper;

    @Test
    public void getPostsByMemberTest(){
        Long memberId = 41L;

        postService.getListByMember(memberId).stream().map(PostDTO::toString).forEach(log::info);
    }

    @Test
    public void getPostsTest(){
        postService.getList().stream().map(PostDTO::toString).forEach(log::info);
    }
}
