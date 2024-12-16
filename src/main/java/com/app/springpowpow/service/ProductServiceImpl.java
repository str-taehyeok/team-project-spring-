package com.app.springpowpow.service;

import com.app.springpowpow.domain.DeliveryVO;
import com.app.springpowpow.domain.ProductDTO;
import com.app.springpowpow.domain.ProductFileVO;
import com.app.springpowpow.domain.ProductVO;
import com.app.springpowpow.repository.DeliveryDAO;
import com.app.springpowpow.repository.ProductDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Override
    public void insertNewProduct(ProductVO productVO) {
        productDAO.save(productVO);
    }


    @Override
    public Optional<ProductDTO> selectProductById(Long id) {
        return productDAO.findProductById(id);
    }

    @Override
    public List<ProductDTO> selectAllProducts() {
        return productDAO.findAllProduct();
    }

    public List<ProductDTO> selectProductsByAnimal() {
        return productDAO.findProductByAnimal();
    }


    @Override
    public List<ProductDTO> selectAllProductsBySellerId(Long memberId) {
        return productDAO.findAllProductBySeller(memberId);
    }

    @Override
    public void updateProduct(ProductDTO productDTO) {
        productDAO.updateProduct(productDTO);
    }


    @Override
    public void deleteProduct(Long id) {
        productDAO.deleteProduct(id);
    }


    @Override
    public void insertNewImage(ProductFileVO productFileVO) {
        productDAO.saveImage(productFileVO);
    }

//    모든 사진 리스트로
    @Override
    public List<ProductFileVO> getList() {
        return productDAO.findAll();
    }

    @Override
    public void updateImage(ProductFileVO productFileVO) {
        productDAO.updateImage(productFileVO);
    }

    @Override
    public void deleteImage(Long productId) {
        productDAO.deleteImage(productId);
    }
}
