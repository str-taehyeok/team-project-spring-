package com.app.springpowpow.member;

import com.app.springpowpow.domain.MemberVO;
import com.app.springpowpow.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class findPasswordTests {

    @Autowired
    private MemberService memberService;

    
//    성공
    @Test
    public void findPassword() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("test001@naver.com");
        memberVO.setMemberPassword("password001!");
        boolean result = memberService.checkPassword(memberVO);
        if (result) {
            log.info("패스워드 일치!. Result: {}", result);
        } else {
            log.error("패스워드 불일치!. Result: {}", result);
        }
    }
}

