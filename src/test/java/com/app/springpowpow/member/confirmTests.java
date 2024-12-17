package com.app.springpowpow.member;

import com.app.springpowpow.mapper.CommLikesMapper;
import com.app.springpowpow.mapper.CommentMapper;
import com.app.springpowpow.mapper.PostMapper;
import com.app.springpowpow.mapper.ProductLikesMapper;
import com.app.springpowpow.repository.CommLikesDAO;
import com.app.springpowpow.service.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class confirmTests {

    @Autowired
    private CommLikesService commLikesService;
    @Autowired
    private CommLikesDAO commLikesDAO;
    @Autowired
    private CommLikesMapper commLikesMapper;
    @Autowired
    private ProductLikesService productLikesService;
    @Autowired
    private ProductService productService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private PostService postService;

    @Test
    public void likeTest(){
        log.info("유저 {}", postMapper.selectAllPopular());
    }

}
