package com.app.springpowpow.member;

import com.app.springpowpow.domain.MemberVO;
import com.app.springpowpow.mapper.MemberMapper;
import com.app.springpowpow.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MemberMapperTests {

    @Autowired
    private MemberService memberService;

    @Test
    public void buyerInsertTest(){
        MemberVO memberVO = new MemberVO();

        memberVO.setId(1L);
        memberVO.setMemberEmail("test15523@naver.com");
        memberVO.setMemberPassword("test123!@#");
        memberVO.setMemberName("김태혁");
        memberVO.setMemberPhone("01000000000");
        memberVO.setMemberNickname("null");
        memberVO.setMemberZipcode("123456");
        memberVO.setMemberAddress("서울시 강남구");
        memberVO.setMemberAddressDetail("역삼동 111-111");
        memberVO.setMemberImage("null");
        memberVO.setMemberSmsCheck('0');
        memberVO.setMemberEmailCheck('0');
        memberVO.setMemberBusinessNumber("null");
        memberVO.setMemberProvider("구매자");
        memberVO.setMemberDate("2022-02-02");
        memberVO.setMemberAuth(0);

        memberService.register(memberVO);
    }

    @Test
    public void getMemberTest(){
        memberService.getMemberById(24L).map(MemberVO::toString).ifPresent(log::info);
    }

    @Test
    public void deleteMemberTest(){

        memberService.withdraw(23L);
    }

    @Test
    public void findEmailByPhoneTest(){
        String memberPhone = "010-1234-5678";
        String email = memberService.findEmailByMemberPhone(memberPhone);
        log.info(email);
    }


}
