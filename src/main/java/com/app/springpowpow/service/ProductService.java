package com.app.springpowpow.service;

import com.app.springpowpow.domain.*;

import java.util.List;
import java.util.Optional;

public interface ProductService {

//     상품 등록
    public void register(ProductVO productVO, List<ProductFileVO> productFiles, DeliveryVO deliveryVO);
//    상품 단일
    public Optional<ProductDTO> selectProductById(Long id);

//    상품 전체
    public List<ProductDTO> selectAllProducts();

//    종으로 분리한 리스트
    public List<ProductDTO> selectProductsByAnimal();

    //    최신 등록된 Id 조회
    public Long getRecentId();

//    특정 판매자의 상품전체
    public List<ProductDTO> selectAllProductsBySellerId(Long memberId);

//    상품 수정
    public void update(ProductDTO productDTO, List<ProductFileVO> productFiles, DeliveryDTO deliveryDTO);

//    상품 삭제
    public void deleteProduct(Long id);


    public void deleteAllProducts(Long memberId);
}
