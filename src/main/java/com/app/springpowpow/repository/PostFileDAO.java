package com.app.springpowpow.repository;

import com.app.springpowpow.domain.PostFileVO;
import com.app.springpowpow.mapper.PostFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostFileDAO {
    public final PostFileMapper postFileMapper;
    //        사진 추가
    public void saveImage(PostFileVO postFileVO) {
        postFileMapper.insertImage(postFileVO);
    }

    //    사진 수정
    public void updateImage(PostFileVO postFileVO) {
        postFileMapper.updateImage(postFileVO);
    }

    //    제품 삭제시 사진도 삭제
    public void deleteAllImage(Long postId) {
        postFileMapper.deleteAllImage(postId);
    }

    //    모든 사진
    public List<PostFileVO> findAll() {

        return postFileMapper.selectAllImage();
    }

}
