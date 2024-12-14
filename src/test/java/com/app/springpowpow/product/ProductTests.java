package com.app.springpowpow.product;

import com.app.springpowpow.domain.CouponVO;
import com.app.springpowpow.domain.ProductDTO;
import com.app.springpowpow.domain.ProductVO;
import com.app.springpowpow.mapper.CouponMapper;
import com.app.springpowpow.mapper.ProductMapper;
import com.app.springpowpow.repository.CouponDAO;
import com.app.springpowpow.service.CouponService;
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



    @Test
    public void selectAllTest() {
        log.info(productService.selectAllProducts().toString());
    }


//    productService 테스트 통과
    @Test
    public void ProductTest() {

        ProductVO productVO = new ProductVO();

        productVO.setId(50L);
        productVO.setMemberId(30L);
        productVO.setProductName("test12412");
        productVO.setProductPrice(2000);
        productVO.setProductCode("101010");
        productVO.setProductDate("2024-01-01");
        productVO.setProductStock(300);
        productVO.setProductEditDate("2024-12-12");
        productVO.setProductDetail("detail");
        productVO.setProductImage1("test.png");
        productVO.setProductImage2("test2.png");
        productVO.setProductImage3("test3.png");
        productVO.setProductImage4("test4.png");
        productVO.setProductAnimal("dog");
        productVO.setProductCategory("healthcare");
        productVO.setProductColor("orange");
        productVO.setProductSize('m');


        productService.insertNewProduct(productVO);

    }
}
