package com.app.springpowpow.repository;

import com.app.springpowpow.domain.OrderDTO;
import com.app.springpowpow.domain.OrderVO;
import com.app.springpowpow.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderDAO {

        private final OrderMapper orderMapper;


        public void save(OrderVO orderVO) {
            orderMapper.insert(orderVO);
        }

        public List<OrderDTO> findAll(Long memberId) {
            return orderMapper.selectAll(memberId);
        }

        public Optional<OrderDTO> findById(Long id) {
            return orderMapper.select(id);
        }

        public void deleteById(Long id) {
            orderMapper.delete(id);
        }

        public void update(OrderVO orderVO) {
            orderMapper.update(orderVO);
        }

        public Optional<OrderDTO> findByOrderDate(String orderDate) {
            return orderMapper.selectAllByDate(orderDate);
        }

    }

