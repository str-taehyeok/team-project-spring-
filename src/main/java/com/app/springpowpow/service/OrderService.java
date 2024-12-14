package com.app.springpowpow.service;

import com.app.springpowpow.domain.OrderDTO;
import com.app.springpowpow.domain.OrderVO;
import com.app.springpowpow.domain.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    public void insertOrder(OrderVO orderVO);
    public List<OrderDTO> selectAllOrders();
    public Optional<OrderDTO> getOrderById(Long id);
    public void deleteOrderById(Long id);
}
