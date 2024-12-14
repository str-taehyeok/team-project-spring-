package com.app.springpowpow.order;


import com.app.springpowpow.domain.OrderDTO;
import com.app.springpowpow.domain.OrderVO;
import com.app.springpowpow.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class OrderTests {

    @Autowired
    private OrderService orderService;

    @Test
    public void insertTest() {
        OrderVO orderVO = new OrderVO();

        orderVO.setId(1L);
        orderVO.setProductId(1L);
        orderVO.setMemberId(1L);
        orderVO.setDeliveryId(1L);
        orderVO.setOrderNumber("1234556");
        orderVO.setOrderAddress("서초구 방배동 123");
        orderVO.setProductCount(3);
//        orderDTO.setOrderDate("2024-12-12");
//        orderDTO.setMemberEmail("jane@gmail.com");
//        orderDTO.setTotalPrice(6000);
//        orderDTO.setProductPrice(2000);
//        orderDTO.setMemberName("jane");
        orderService.insertOrder(orderVO);
    }

    @Test
    public void selectAllTest() {
        log.info(orderService.selectAllOrders().toString());
    }
}
