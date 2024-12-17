package com.app.springpowpow.service;

import com.app.springpowpow.domain.PostDTO;
import com.app.springpowpow.domain.PostVO;

import java.util.List;
import java.util.Optional;

public interface PostService {


//    게시글 전체조회
    public List<PostDTO> getList();

//    인기순 게시글 조회 (4개)
    public List<PostDTO> getListPopular();

//    게시글 단일 조회
    public Optional<PostDTO> getPost(Long id);

//    게시글 작성
    public void write(PostVO postVO);

//    게시글 수정
    public void modify(PostVO postVO);

//    게시글 삭제
    public void remove(Long id);

//    회원탈퇴시 모든 게시글 삭제
    public void removeAll(Long memberId);
    
}
