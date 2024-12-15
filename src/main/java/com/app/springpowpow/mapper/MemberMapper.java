package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    //    회원 조회
    public Optional<MemberVO> select(Long id);

    //    전체 조회
    public List<MemberVO> selectAll();

    //    이메일로 아이디 찾기
    public Long selectById(String memberEmail);

    //    회원 추가
    public void insert(MemberVO memberVO);

    //    회원 수정
    public void update(MemberVO memberVO);

    //    회원 탈퇴
    public void delete(Long id);

    // 이메일 중복체크
    public int checkEmailExists(String memberEmail);

    // 전화번호로 이메일 찾기
    public String selectEmailByPhone(String memberPhone);

    // 이메일로 회원 정보 조회
    public Optional<MemberVO> findMemberByEmail(String memberEmail);

//    어드민구매자 전체조회
    public List<MemberVO> selectBuyers();

//    어드민 판매자 전체 조회
    public List<MemberVO> selectSellers();

//    비밀번호 변경
    public void updatePassword(MemberVO memberVO);

}
