package com.app.springpowpow.service;

import com.app.springpowpow.domain.MemberVO;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    public Optional<MemberVO> getMemberById(Long id);

    public List<MemberVO> getAllMembers();

    public Long getMemberIdByEmail(String memberEmail);

    public void register(MemberVO memberVO);

    public void modify(MemberVO memberVO);

    public void withdraw(Long id);

    public boolean checkDuplicate(String memberEmail);

    // 이름 & 휴대번호 ID 조회
    public Optional<MemberVO> findMemberByNameAndPhone(MemberVO memberVO);

    // 휴대폰 번호로 이메일 조회
    public Optional<String> getEmailById(String memberPhone);

    // 이메일로 회원 정보 조회
    public List<MemberVO> findMemberByEmail(String memberEmail);

    // 이메일 조회
    public Optional<MemberVO> findById(Long id);

}
