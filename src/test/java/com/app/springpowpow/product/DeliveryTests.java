package com.app.springpowpow.product;

import com.app.springpowpow.domain.ProductDTO;
import com.app.springpowpow.service.DeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class DeliveryTests {

    @Autowired
    private DeliveryService deliveryService;

    @Test
    public void testInsertDelivery() {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(1L);
        productDTO.setMemberId(1L);
        productDTO.setDeliveryFeeKind("free");
        productDTO.setDeliveryFee(2000);
        productDTO.setDeliveryFeeFree(0);
        productDTO.setDeliveryHow("normalDelivery");
        productDTO.setDeliveryPayWhen("prePay");
        productDTO.setDeliveryCompany("lotte");

        deliveryService.insertDeliveryInfo(productDTO);
    }
}
