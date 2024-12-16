package com.app.springpowpow.repository;

import com.app.springpowpow.domain.OrderCancelDTO;
import com.app.springpowpow.domain.OrderCancelVO;
import com.app.springpowpow.mapper.OrderCancelMapper;
import com.app.springpowpow.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderCancelDAO {

    private final OrderCancelMapper orderCancelMapper;

    public void save(OrderCancelVO orderCancelVO) {
        orderCancelMapper.insert(orderCancelVO);
    }
    public List<OrderCancelVO> findAll() {
     return orderCancelMapper.selectAll();
    }

    public Optional<OrderCancelVO> findById(Long id) {
        return orderCancelMapper.select(id);
    }

    public void deleteById(Long id) {
        orderCancelMapper.delete(id);
    }
    public void update(OrderCancelVO orderCancelVO) {
        orderCancelMapper.update(orderCancelVO);
    }
    public Optional<OrderCancelDTO> findByOrderCancelDate(String orderCancelDate) {
        return orderCancelMapper.selectAllByDate(orderCancelDate);
    }
}
