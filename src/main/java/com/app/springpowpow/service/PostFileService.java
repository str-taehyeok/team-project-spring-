package com.app.springpowpow.service;

import com.app.springpowpow.domain.PostFileVO;
import com.app.springpowpow.domain.ProductFileVO;

import java.util.List;

public interface PostFileService {
    //    이미지 추가
    public void insertNewImage(PostFileVO postFileVO);

    //    모든 사진
    public List<PostFileVO> getList();

    //    이미지 수정
    public void updateImage(PostFileVO postFileVO);

    //    상품 삭제되면 사진도 삭제
    public void deleteImage(Long postId);
}
