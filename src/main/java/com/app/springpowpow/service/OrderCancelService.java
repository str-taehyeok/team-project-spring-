package com.app.springpowpow.service;

import com.app.springpowpow.domain.OrderCancelDTO;
import com.app.springpowpow.domain.OrderCancelVO;

import java.util.List;
import java.util.Optional;

public interface OrderCancelService {

    public void insertOrderCancel(OrderCancelVO orderCancelVO);
    public List<OrderCancelVO> findAll();
    public Optional<OrderCancelVO> findById(Long id);
    public void deleteById(Long id);
    public void updateOrderCancel(OrderCancelVO orderCancelVO);
    public Optional<OrderCancelDTO> findByOrderCancelDate(String orderCancelDate);
}

