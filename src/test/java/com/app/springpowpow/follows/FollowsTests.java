package com.app.springpowpow.follows;

import com.app.springpowpow.domain.FollowsDTO;
import com.app.springpowpow.domain.FollowsVO;
import com.app.springpowpow.service.FollowsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class FollowsTests {

    @Autowired
    private FollowsService followsService;

    @Test
    public void insertTest() {

        FollowsVO followsVO = new FollowsVO();
        FollowsDTO followsDTO = new FollowsDTO();

        followsVO.setId(1L);
        followsVO.setFollowsFollowState("1");
        followsVO.setFollowerMemberId(3L);
        followsVO.setFollowingMemberId(3L);

        followsService.addFollow(followsVO);
    }

}
