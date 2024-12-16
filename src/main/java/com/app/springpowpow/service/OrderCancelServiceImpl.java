package com.app.springpowpow.service;

import com.app.springpowpow.domain.OrderCancelDTO;
import com.app.springpowpow.domain.OrderCancelVO;
import com.app.springpowpow.repository.OrderCancelDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class OrderCancelServiceImpl implements OrderCancelService {

    private final OrderCancelDAO orderCancelDAO;

    @Override
    public void insertOrderCancel(OrderCancelVO orderCancelVO) {
        orderCancelDAO.save(orderCancelVO);
    }

    @Override
    public List<OrderCancelVO> findAll() {
        return orderCancelDAO.findAll();
    }

    @Override
    public Optional<OrderCancelVO> findById(Long id) {
        return orderCancelDAO.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        orderCancelDAO.deleteById(id);
    }

    @Override
    public void updateOrderCancel(OrderCancelVO orderCancelVO) {
        orderCancelDAO.update(orderCancelVO);
    }

    @Override
    public Optional<OrderCancelDTO> findByOrderCancelDate(String orderCancelDate) {
        return orderCancelDAO.findByOrderCancelDate(orderCancelDate);
    }
}
