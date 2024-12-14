package com.app.springpowpow.repository;

import com.app.springpowpow.domain.PostDTO;
import com.app.springpowpow.domain.PostVO;
import com.app.springpowpow.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostDAO {

    private final PostMapper postMapper;

//    게시글 전체 가져오기
    public List<PostDTO> findAll(){
        return postMapper.selectAll();
    }

//    게시글 단일 가져오기
    public Optional<PostDTO> findById(Long id){
        return postMapper.select(id);
    }

//    게시글 작성
    public void save(PostVO postVO){
        postMapper.insert(postVO);
    }

//    게시글 수정
    public void update(PostVO postVO){
        postMapper.update(postVO);
    }

//    게시글 삭제
    public void delete(Long id){
        postMapper.delete(id);
    }

//    회원탈퇴시 모든 게시글 삭제
    public void removeAll(Long memberId){
        postMapper.deleteAll(memberId);
    }


}
