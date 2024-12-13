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

    public Optional<MemberVO> findMemberByNameAndPhone(MemberVO memberVO);

    public Optional<MemberVO> getEmailById(Long id);

}
