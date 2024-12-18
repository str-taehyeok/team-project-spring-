package com.app.springpowpow.order;


import com.app.springpowpow.domain.OrderDTO;
import com.app.springpowpow.domain.OrderVO;
import com.app.springpowpow.mapper.OrderMapper;
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
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void insertTest() {

        OrderVO orderVO = new OrderVO();

        orderVO.setId(2L);
        orderVO.setMemberId(1L);
        orderVO.setProductId(1L);
        orderVO.setDeliveryId(1L);
        orderVO.setOrderNumber("1234556");
        orderVO.setOrderAddress("서초구 방배동 123");
        orderVO.setOrderDate("2024-12-16");
        orderVO.setOrderMemo("메모에오");
        orderVO.setProductCount(3);
        orderService.insertNewOrder(orderVO);
    }


//    통과
    @Test
    public void selectAllTest() {
        orderService.selectAllOrders(16L).stream().map(OrderDTO::toString).forEach(log::info);
    }

//    통과
    @Test
    public void selectOneTest() {
        log.info(orderService.getOrderById(1L).toString());
    }

//    통과
    @Test
    public void selectOneByMemberId() {
        log.info(orderMapper.selectByMemberId(2L).toString());
    }
}


