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

    public Long selectByNameAndPhone(MemberVO memberVO) {
        return memberMapper.selectByNameAndPhone(memberVO);
    }

    public List<MemberVO> findEmailById(Long id) {
        return memberMapper.selectByEmail(id);
    }

    public Optional<String> findEmailByPhone(String memberPhone) {
        return memberMapper.findEmailByPhone(memberPhone);
    }

    public List<MemberVO> findEmailByEmail(String memberEmail) {
        return memberMapper.findPhoneByEmail(memberEmail);
    }
}
