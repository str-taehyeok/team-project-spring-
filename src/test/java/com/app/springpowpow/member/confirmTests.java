package com.app.springpowpow.member;

import com.app.springpowpow.mapper.CommLikesMapper;
import com.app.springpowpow.repository.CommLikesDAO;
import com.app.springpowpow.service.CommLikesService;
import com.app.springpowpow.service.MemberService;
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

    @Test
    public void likeTest(){
       log.info("like {}", commLikesService.getAllLikes(1L));
//       log.info("like {}", commLikesDAO.getAllLikes(1L));
        Long memberId = 1L;
//       log.info("like {}", commLikesMapper.selectAllLikes(memberId));
    }
}
