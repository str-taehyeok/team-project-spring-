package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OrderMapper {

    public void insertOrder(OrderDTO orderDTO);

    public List<OrderDTO> selectOrder();

    public Optional<OrderDTO> selectOrderById(Long id);

    public void deleteOrder(Long id);
}
