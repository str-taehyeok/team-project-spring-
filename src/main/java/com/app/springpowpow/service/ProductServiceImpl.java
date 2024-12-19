package com.app.springpowpow.service;

<<<<<<< HEAD
import com.app.springpowpow.domain.*;
=======
import com.app.springpowpow.domain.DeliveryVO;
import com.app.springpowpow.domain.ProductDTO;
import com.app.springpowpow.domain.ProductVO;
>>>>>>> 507af47d77f2728d354ab2a1db9d7ebe704e43c4
import com.app.springpowpow.repository.DeliveryDAO;
import com.app.springpowpow.repository.ProductDAO;
import com.app.springpowpow.repository.ProductFileDAO;
import com.app.springpowpow.repository.ReviewDAO;
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
    private final ProductFileDAO productFileDAO;
    private final DeliveryDAO deliveryDAO;
    private final ReviewDAO reviewDAO;
    private final DeliveryVO deliveryVO;


    @Override
    public void register(ProductVO productVO, List<ProductFileVO> productFiles, DeliveryVO deliveryVO) {
        productDAO.save(productVO);
        for (ProductFileVO productFile : productFiles) {
            productFileDAO.saveImage(productFile);
        }
        deliveryDAO.save(deliveryVO);
    }

    @Override
    public Long getRecentId() {
        return productDAO.findRecentId();
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


//    @Override
//    public void deleteProduct(Long id) {
//        productFileDAO.deleteImage(id);
//        productDAO.deleteProduct(id);
//    }

    @Override
    public void deleteProduct(Long id) {
        productFileDAO.deleteAllImage(id);
        reviewDAO.deleteAll(id);
        deliveryDAO.deleteAll(id);
        productDAO.deleteProduct(id);
    }

    @Override
    public void deleteAllProducts(Long memberId){
        productDAO.deleteAllProducts(memberId);
    }

}
