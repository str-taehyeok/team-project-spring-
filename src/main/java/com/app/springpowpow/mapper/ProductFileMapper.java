package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.ProductFileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductFileMapper {

    //    사진 추가
    public void insertImage(ProductFileVO productFileVO);

    //    SELECT ALL
    public List<ProductFileVO> selectAllImage();

    //    사진 수정
    public void updateImage(ProductFileVO productFileVO);


    //    제품 삭제시 사진도 삭제
    public void deleteAllImage(Long productId);

}
