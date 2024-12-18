package com.app.springpowpow.service;

import com.app.springpowpow.domain.ProductFileVO;
import com.app.springpowpow.repository.ProductFileDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ProductFileServiceImpl implements ProductFileService {

    private final ProductFileDAO productFileDAO;

    @Override
    public void insertNewImage(ProductFileVO productFileVO) {
        productFileDAO.saveImage(productFileVO);
    }

    //    모든 사진 리스트로
    @Override
    public List<ProductFileVO> getList() {
        return productFileDAO.findAll();
    }

    @Override
    public void updateImage(ProductFileVO productFileVO) {
        productFileDAO.updateImage(productFileVO);
    }

    @Override
    public void deleteImage(Long productId) {
        productFileDAO.deleteAllImage(productId);
    }
}
