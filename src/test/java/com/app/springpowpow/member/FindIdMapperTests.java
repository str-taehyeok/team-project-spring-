package com.app.springpowpow.member;

import com.app.springpowpow.domain.MemberVO;
import com.app.springpowpow.mapper.MemberMapper;
import com.app.springpowpow.repository.MemberDAO;
import com.app.springpowpow.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class FindIdMapperTests {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MemberDAO memberDAO;


    @Test
    public void findIdTest() {

        MemberVO memberVO = new MemberVO();

        memberVO.setId(1L);
        memberVO.setMemberName("이진아");
        memberVO.setMemberPhone("01024003252");
        
        memberService.modify(memberVO);

//        MemberVO foundMember = memberService.findMemberByNameAndPhone(name, phone);
//        log.info("찾고자하는 아이디 : {}", foundMember);
    }
}
