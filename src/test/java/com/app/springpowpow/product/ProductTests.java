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

}
