package com.app.springpowpow.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {



//    회원탈퇴시 게시물 삭제
    public void deleteAll(Long memberId);
}
