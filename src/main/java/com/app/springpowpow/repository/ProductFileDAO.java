package com.app.springpowpow.repository;

import com.app.springpowpow.domain.ProductFileVO;
import com.app.springpowpow.mapper.ProductFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductFileDAO {

    private final ProductFileMapper productFileMapper;

    //        사진 추가
    public void saveImage(ProductFileVO productFileVO) {
        productFileMapper.insertImage(productFileVO);
    }

    //    사진 수정
    public void updateImage(ProductFileVO productFileVO) {
        productFileMapper.updateImage(productFileVO);
    }

    //    제품 삭제시 사진도 삭제
    public void deleteImage(Long productId) {
        productFileMapper.deleteAllImage(productId);
    }

    //    모든 사진
    public List<ProductFileVO> findAll() {
        return productFileMapper.selectAllImage();
    }

}
