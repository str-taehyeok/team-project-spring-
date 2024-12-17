package com.app.springpowpow.service;

import com.app.springpowpow.domain.OrderDTO;
import com.app.springpowpow.domain.OrderVO;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    public void insertNewOrder(OrderVO orderVO);
    public List<OrderDTO> selectAllOrders();
    public Optional<OrderDTO> getOrderById(Long id);
    public void deleteOrderById(Long id);
    public void updateOrderById(OrderVO orderVO);
    public List<OrderVO> selectOrderByMemberId(Long memberId);
    public Optional<OrderDTO> selectOrderByDate(String orderDate);
}
