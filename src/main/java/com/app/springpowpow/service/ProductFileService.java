package com.app.springpowpow.service;

import com.app.springpowpow.domain.ProductFileVO;

import java.util.List;

public interface ProductFileService {

    //    이미지 추가
    public void insertNewImage(ProductFileVO productFileVO);

    //    모든 사진
    public List<ProductFileVO> getList();

    //    이미지 수정
    public void updateImage(ProductFileVO productFileVO);

    //    상품 삭제되면 사진도 삭제
    public void deleteImage(Long productId);
}
