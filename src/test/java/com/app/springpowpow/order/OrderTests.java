package com.app.springpowpow.order;


import com.app.springpowpow.domain.OrderDTO;
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
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(1L);
        orderDTO.setMemberId(1L);
        orderDTO.setProductId(5L);
        orderDTO.setDeliveryId(1L);
        orderDTO.setOrderAddress("서초구 방배동 123");
        orderDTO.setOrderNumber("123455678");
        orderDTO.setProductCount(3);
//        orderDTO.setOrderDate("2024-12-12");
//        orderDTO.setMemberEmail("jane@gmail.com");
//        orderDTO.setTotalPrice(6000);
//        orderDTO.setProductPrice(2000);
//        orderDTO.setMemberName("jane");


        orderService.insertOrder(orderDTO);
    }

    @Test
    public void selectAllTest() {
        log.info(orderService.selectAllOrders().toString());
    }
}
