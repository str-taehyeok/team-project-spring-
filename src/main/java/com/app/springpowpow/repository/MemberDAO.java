package com.app.springpowpow.repository;

import com.app.springpowpow.domain.MemberVO;
import com.app.springpowpow.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MemberDAO {

    private final MemberMapper memberMapper;

    public Optional<MemberVO> findById(Long id){
        return memberMapper.select(id);
    }

    public Optional<MemberVO> findByIdAndType(Long id, String type){
        return memberMapper.selectType(id, type);
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
        return memberMapper.selectEmailByPhone(memberPhone);
    }
    // 이메일로 회원 정보 조회
    public Optional<MemberVO> findMember(String memberEmail) {
        return memberMapper.findMemberByEmail(memberEmail);
    }

//    어드민 구매자 리스트
//    public List<MemberVO> findBuyersOnly() {
//        List<MemberVO> members = memberMapper.selectAll();
//        List<MemberVO> classifyMembers = members.stream()
//                .map(member -> {
//                    if("구매자".equals(member.getMemberProvider())){
//                        member.setMemberProvider("구매자");
//                    }else {
//                        member.setMemberProvider("판매자");
//                    }
//                    return member;
//                })
//                .collect(Collectors.toList());
//        return classifyMembers;
//    }

    // 구매자 리스트 조회
    public List<MemberVO> findBuyersOnly() {
        List<MemberVO> members = memberMapper.selectAll();
        return members.stream()
                .filter(member -> "구매자".equals(member.getMemberProvider()))
                .collect(Collectors.toList());
    }

    // 판매자 리스트 조회
    public List<MemberVO> findSellersOnly() {
        List<MemberVO> members = memberMapper.selectAll();
        return members.stream()
                .filter(member -> !"구매자".equals(member.getMemberProvider()))
                .collect(Collectors.toList());
    }

    // 비밀번호 변경
    public void updatePassword(MemberVO memberVO) {
        memberMapper.updatePassword(memberVO);
    }

}
