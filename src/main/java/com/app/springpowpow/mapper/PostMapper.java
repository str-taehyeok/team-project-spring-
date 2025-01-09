package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.PostDTO;
import com.app.springpowpow.domain.PostVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {

//   전체 조회
    public List<PostDTO> selectAll();

//   좋아요 순 4개
    public List<PostDTO> selectAllPopular();

//    최신 등록된 Id 조회
    public Long selectRecentId();

//    멤버별 전체 조회
    public List<PostDTO> selectAllByMember(Long memberId);

//   단일 조회
    public Optional<PostDTO> select(Long id);

//   게시글 작성
    public void insert(PostVO postVO);

//   게시글 수정
    public void update(PostVO postVO);

//    게시글 삭제
    public void delete(Long id);

//    회원탈퇴시 게시물 삭제
    public void deleteAll(Long memberId);
}
