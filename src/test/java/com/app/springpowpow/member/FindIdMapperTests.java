//package com.app.springpowpow.member;
//
//import com.app.springpowpow.domain.MemberVO;
//import com.app.springpowpow.mapper.MemberMapper;
//import com.app.springpowpow.service.MemberService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@Slf4j
//public class FindIdMapperTests {
//
//    @Autowired
//    private MemberService memberService;
//
//    @Autowired
//    private MemberMapper memberMapper;
//
//    private MemberVO memberVO;
//
//    @Test
//    public void FindIdMapperTests() {
//
//
//        memberVO.setMemberEmail("gina@naver.com");
//        memberVO.setMemberPhone("01024003252");
//        memberVO.setMemberName("이진아");
//        memberVO.setMemberNickname("찹쌀징어");
//
//        memberVO.setMemberEmail("ryu@naver.com");
//        memberVO.setMemberPhone("01024003253");
//        memberVO.setMemberName("류재은");
//        memberVO.setMemberNickname("대마왕");
//
//        memberVO.setMemberEmail("min@naver.com");
//        memberVO.setMemberPhone("01024003254");
//        memberVO.setMemberName("신민철");
//        memberVO.setMemberNickname("민철게이");
//
//        // 회원 데이터를 삽입
//        memberMapper.insert(memberVO);
//        memberMapper.insert(memberVO);
//        memberMapper.insert(memberVO);
//    }
//
//    @Test
//    public void testFindMemberByIdAndPhone() {
//        // 정상적인 휴대폰 번호로 이메일을 찾아 회원 정보를 조회하는 테스트
//
//        // 이메일 찾기
//        Optional<String> memberEmailOptional = memberService.getEmailById(memberVO.getMemberPhone());
//        assertThat(memberEmailOptional).isPresent();
//        String memberEmail = memberEmailOptional.get();
//
//        // 이메일로 회원 조회
//        List<MemberVO> members = memberService.findMemberByEmail(memberEmail);
//
//        // 회원이 존재하면 첫 번째 회원 반환
//        MemberVO foundMember = members.get(0);
//        assertThat(foundMember).isNotNull();
//        assertThat(foundMember.getMemberEmail()).isEqualTo(memberVO.getMemberEmail());
//        assertThat(foundMember.getMemberPhone()).isEqualTo(memberVO.getMemberPhone());
//
//        // 존재하지 않는 회원을 조회하는 테스트
//        String nonExistentPhone = "01024003299"; // 존재하지 않는 전화번호
//        Optional<String> nonExistentEmail = memberService.getEmailById(nonExistentPhone);
//        assertThat(nonExistentEmail).isEmpty();
//
//        // 이메일로 회원 조회 (결과가 없을 때)
//        List<MemberVO> emptyMembers = memberService.findMemberByEmail(nonExistentEmail.orElse(""));
//        assertThat(emptyMembers).isEmpty();
//    }
//}
