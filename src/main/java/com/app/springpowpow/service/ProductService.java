package com.app.springpowpow.service;

import com.app.springpowpow.domain.DeliveryVO;
import com.app.springpowpow.domain.ProductDTO;
import com.app.springpowpow.domain.ProductFileVO;
import com.app.springpowpow.domain.ProductVO;

import java.util.List;
import java.util.Optional;

public interface ProductService {

//     상품 등록
    public void insertNewProduct(ProductVO productVO);
//    상품 단일
    public Optional<ProductDTO> selectProductById(Long id);

//    상품 전체
    public List<ProductDTO> selectAllProducts();

//    종으로 분리한 리스트
    public List<ProductDTO> selectProductsByAnimal();


//    특정 판매자의 상품전체
    public List<ProductDTO> selectAllProductsBySellerId(Long memberId);

//    상품 수정
    public void updateProduct(ProductDTO productDTO);

//    상품 삭제
    public void deleteProduct(Long id);


    public void deleteAllProducts(Long memberId);
}
