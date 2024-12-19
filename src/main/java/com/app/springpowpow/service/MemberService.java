package com.app.springpowpow.service;

import com.app.springpowpow.domain.MemberVO;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    public Optional<MemberVO> getMemberById(Long id);

    public Optional<MemberVO> getMemberByIdAndType(Long id, String type);

    public List<MemberVO> getAllMembers();

    public Long getMemberIdByEmail(String memberEmail);

    public void register(MemberVO memberVO);

    public void modify(MemberVO memberVO);

    public void withdraw(Long id);

    public boolean checkDuplicate(String memberEmail);

//    판매자 회원탈퇴
    public void withdrawSeller(Long id);

    // 전화번호로 이메일 찾기
    public String findEmail(String memberPhone);

    // 이메일로 회원 정보 조회
    public Optional<MemberVO> findMember(String memberEmail);


//    어드민 구매자리스트
    public List<MemberVO> findBuyers();
//    어드민 판매자 리스트
    public List<MemberVO> findSellers();

//    비밀번호 변경
    public void updatePassword(String memberEmail, String newPassword);

//    비밀번호 확인
    public boolean checkPassword(MemberVO memberVO);


}
