package com.app.springpowpow.product;

import com.app.springpowpow.domain.CouponVO;
import com.app.springpowpow.domain.ProductDTO;
import com.app.springpowpow.domain.ProductFileVO;
import com.app.springpowpow.domain.ProductVO;
import com.app.springpowpow.mapper.CouponMapper;
import com.app.springpowpow.mapper.ProductMapper;
import com.app.springpowpow.repository.CouponDAO;
import com.app.springpowpow.service.CouponService;
import com.app.springpowpow.service.ProductFileService;
import com.app.springpowpow.service.ProductService;
import com.app.springpowpow.service.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class ProductTests {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductFileService productFileService;

    @Test
    public void selectAllTest() {
        log.info(productService.selectAllProducts().toString());
    }


    @Test
    public void insertImageTest(){
        ProductFileVO productFileVO = new ProductFileVO();

        productFileVO.setId(2L);
        productFileVO.setProductId(3L);
        productFileVO.setProductFileName("test.jpg");
        productFileVO.setProductFilePath("test.jpg");

        productFileService.updateImage(productFileVO);
    }
}
