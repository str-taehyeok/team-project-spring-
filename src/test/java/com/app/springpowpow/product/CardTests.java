package com.app.springpowpow.product;

import com.app.springpowpow.service.CardService;
import com.app.springpowpow.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class CardTests {
    @Autowired
    private CardService cardService;

    @Test
    public void CardTest(){
//        log.info("cart {}", cardService.getCard(1L));
//        cardService.remove(1L);


    }
}
