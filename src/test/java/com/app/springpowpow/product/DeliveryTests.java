package com.app.springpowpow.product;

import com.app.springpowpow.domain.DeliveryVO;
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


//    배송 서비스 테스트 통과 완료
    @Test
    public void testInsertDelivery() {
        DeliveryVO deliveryVO = new DeliveryVO();

        deliveryVO.setId(1L);
        deliveryVO.setProductId(30L);
        deliveryVO.setDeliveryFeeKind("free");
        deliveryVO.setDeliveryFee(2000);
        deliveryVO.setDeliveryFeeFree(0);
        deliveryVO.setDeliveryHow("normalDelivery");
        deliveryVO.setDeliveryPayWhen("prePay");
        deliveryVO.setDeliveryCompany("lotte");

        deliveryService.insertDeliveryInfo(deliveryVO);
    }
}
