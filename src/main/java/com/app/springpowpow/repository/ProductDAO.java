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
        public void save(ProductDTO productDTO) {
            productMapper.insert(productDTO);
        }


//        단일 제품
        public Optional<ProductDTO> findProductById(Long id) {
            return productMapper.select(id);
        }

//        제품 전체
        public List<ProductDTO> findAllProduct() {
            return productMapper.selectAll();
        }

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


    }

