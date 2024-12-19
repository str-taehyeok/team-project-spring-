package com.app.springpowpow.service;

import com.app.springpowpow.domain.PostDTO;
import com.app.springpowpow.domain.PostFileVO;
import com.app.springpowpow.domain.PostVO;
import com.app.springpowpow.repository.PostDAO;
import com.app.springpowpow.repository.PostFileDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class PostServiceImpl implements PostService {

    private final PostDAO postDAO;
    private final PostFileDAO postFileDAO;

//    게시글 전체조회
    @Override
    public List<PostDTO> getList() {
        return postDAO.findAll();
    }

//    인기 4개
    @Override
    public List<PostDTO> getListPopular() {
        return postDAO.findAllPopular();
    }

    //    최신 등록된 Id 조회
    public Long getRecentId(){
        return postDAO.findRecentId();
    }

    //    게시글 단일 조회
    @Override
    public Optional<PostDTO> getPost(Long id) {
        return postDAO.findById(id);
    }
//    게시글 작성
    @Override
<<<<<<< HEAD
    public void write(PostVO postVO, List<PostFileVO> postFiles) {
        postDAO.save(postVO);
        for (PostFileVO postFile : postFiles) {
            postFileDAO.save(postFile);
        }
=======
    public void save(PostVO postVO) {
        postDAO.write(postVO);
>>>>>>> 507af47d77f2728d354ab2a1db9d7ebe704e43c4
    }
//    게시글 수정
    @Override
    public void modify(PostVO postVO) {
        postDAO.update(postVO);
    }
//    게시글 삭제
    @Override
    public void remove(Long id) {
        postDAO.delete(id);
    }

//  회원탈퇴시 삭제
    @Override
    public void removeAll(Long memberId) {
        postDAO.removeAll(memberId);
    }
}
