package com.app.springpowpow.repository;

import com.app.springpowpow.domain.OrderDTO;
import com.app.springpowpow.domain.OrderVO;
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


        public void save(OrderVO orderVO) {
            orderMapper.insert(orderVO);
        }

        public List<OrderDTO> findAll() {
            return orderMapper.selectAll();
        }

        public Optional<OrderDTO> findById(Long id) {
            return orderMapper.select(id);
        }

        public void deleteById(Long id) {
            orderMapper.delete(id);
        }

    }

