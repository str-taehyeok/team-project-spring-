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
}
