package com.app.springpowpow.repository;

import com.app.springpowpow.domain.MemberVO;
import com.app.springpowpow.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {

    private final MemberMapper memberMapper;

    public Optional<MemberVO> findById(Long id){
        return memberMapper.select(id);
    }

    public List<MemberVO> findAll(){
        return memberMapper.selectAll();
    }

    public Long findByEmail(String memberEmail){
        return memberMapper.selectById(memberEmail);
    }

    public void save(MemberVO memberVO){
        memberMapper.insert(memberVO);
    }

    public void update(MemberVO memberVO){
        memberMapper.update(memberVO);
    }

    public void delete(Long id){
        memberMapper.delete(id);
    }

    public int checkDuplicate(String memberEmail){
        return memberMapper.checkEmailExists(memberEmail);
    }

    // 전화번호로 이메일 찾기
    public String findEmail(String memberPhone) {
        return memberMapper.findEmailByMemberPhone(memberPhone);
    }

    // 이메일로 회원 정보 조회
    public List<MemberVO> findMember(String memberEmail) {
        return memberMapper.findMemberByEmail(memberEmail);
    }

}
