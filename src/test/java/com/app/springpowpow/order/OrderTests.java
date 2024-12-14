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
        orderVO.setProductId(11L);
        orderVO.setMemberId(5L);
        orderVO.setDeliveryId(2L);
        orderVO.setOrderNumber("1234556");
        orderVO.setOrderAddress("서초구 방배동 123");
        orderVO.setProductCount(3);
        orderService.insertOrder(orderVO);
    }


//    통과
    @Test
    public void selectAllTest() {
        log.info(orderService.selectAllOrders().toString());
    }

//    통과
    @Test
    public void selectOneTest() {
        log.info(orderService.getOrderById(1L).toString());
    }
}
