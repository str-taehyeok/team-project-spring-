package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.ProductDTO;
import com.app.springpowpow.domain.ProductFileVO;
import com.app.springpowpow.domain.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductMapper {
//    제품 추가
    public void insert(ProductVO productVO);
//    제품 단일
    public Optional<ProductDTO> select(Long id);
//    제품 리스트
    public List<ProductDTO> selectAll();

//    종에 따른 리스트
    public List<ProductDTO> selectByAnimal();

//    판매자의 모든 제품
    public List<ProductDTO> selectAllBySeller(Long memberId);
//    수정
    public void update(ProductDTO productDTO);
//    삭제
    public void delete(Long id);

//   회원탈퇴시 상품 전체삭제
    public void deleteAll(Long memberId);


//    사진 추가
    public void insertImage(ProductFileVO productFileVO);

//    SELECT ALL
    public List<ProductFileVO> selectAllImage();

//    사진 수정
    public void updateImage(ProductFileVO productFileVO);


//    제품 삭제시 사진도 삭제
    public void deleteAllImage(Long productId);

}
