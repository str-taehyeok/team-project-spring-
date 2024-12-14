package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.OrderDTO;
import com.app.springpowpow.domain.OrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OrderMapper {

    public void insert(OrderVO orderVO);

    public List<OrderDTO> selectOrder();

    public Optional<OrderDTO> selectOrderById(Long id);

    public void deleteOrder(Long id);
}
