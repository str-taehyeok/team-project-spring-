package com.app.springpowpow.product;

import com.app.springpowpow.domain.CartDTO;
import com.app.springpowpow.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class CartTests {

    @Autowired
    private CartService cartService;

    @Test
    public void cartTest(){

//        log.info("cart {}", cartService.getCartById(1L));
//        log.info("cart {}" , cartService.getCartProductById(1L));
        cartService.removeMember(1L);
    }
}
