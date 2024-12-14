package com.app.springpowpow.product;

import com.app.springpowpow.service.ProductService;
import com.app.springpowpow.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ReviewTests {

    @Autowired
    private ReviewService reviewService;



    @Test
    public void selectAllTest() {
        log.info(reviewService.getReviews(1L).toString());
    }


//    @Test
//    public void ProductTest() {
//
//        ProductDTO productDTO = new ProductDTO();
//
//        productDTO.setId(1L);
//        productDTO.setProductName("test");
//        productDTO.setProductPrice(2000);
//        productDTO.setProductCode("101010");
//        productDTO.setProductDate("2024-01-01");
//        productDTO.setProductStock(300);
//        productDTO.setProductEditDate("2024-12-12");
//        productDTO.setProductDetail("detail");
//        productDTO.setProductImage1("test.png");
//        productDTO.setProductImage2("test2.png");
//        productDTO.setProductImage3("test3.png");
//        productDTO.setProductImage4("test4.png");
//        productDTO.setProductAnimal("dog");
//        productDTO.setProductCategory("healthcare");
//        productDTO.setProductColor("orange");
//        productDTO.setProductSize('m');
//        productDTO.setDeliveryFeeFree(0);
//        productDTO.setDeliveryFee(0);
//        productDTO.setDeliveryFeeKind("free");
//        productDTO.setDeliveryHow("normalDelivery");
//        productDTO.setDeliveryPayWhen("prePay");
//        productDTO.setDeliveryCompany("lotte");
//
//
//        productService.insertProduct(productDTO);
//
//    }
}
