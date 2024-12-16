package com.app.springpowpow.repository;

import com.app.springpowpow.domain.ProductDTO;
import com.app.springpowpow.domain.ProductVO;
import com.app.springpowpow.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
public class ProductDAO {

        private final ProductMapper productMapper;


//        제품 등록
        public void save(ProductVO productVO) {
            productMapper.insert(productVO);
        }


//        단일 제품
        public Optional<ProductDTO> findProductById(Long id) {
            return productMapper.select(id);
        }

//        제품 전체
        public List<ProductDTO> findAllProduct() {
            return productMapper.selectAll();
        }

//        종에따른 분류 리스트
        public List<ProductDTO> findProductByAnimal() {return productMapper.selectByAnimal();}


//        판매자의 모든 제품
        public List<ProductDTO> findAllProductBySeller(Long memberId) {
            return productMapper.selectAllBySeller(memberId);
        }

//        제품 수정
        public void updateProduct(ProductDTO productDTO) {
            productMapper.update(productDTO);
        }


//        제품 삭제
        public void deleteProduct(Long id) {
            productMapper.delete(id);
        }

//        회원 탈퇴시 상품 전체 삭제
        public void deleteAllProducts(Long memberId){
            productMapper.deleteAll(memberId);
        }
    }

