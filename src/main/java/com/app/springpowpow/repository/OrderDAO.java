package com.app.springpowpow.repository;

import com.app.springpowpow.domain.OrderDTO;
import com.app.springpowpow.domain.ProductDTO;
import com.app.springpowpow.mapper.OrderMapper;
import com.app.springpowpow.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderDAO {

        private final OrderMapper orderMapper;


        public void save(OrderDTO orderDTO) {
            orderMapper.insertOrder(orderDTO);
        }

        public List<OrderDTO> findAll() {
            return orderMapper.selectOrder();
        }

        public Optional<OrderDTO> findById(Long id) {
            return orderMapper.selectOrderById(id);
        }

        public void deleteById(Long id) {
            orderMapper.deleteOrder(id);
        }

    }

