package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.OrderCancelDTO;
import com.app.springpowpow.domain.OrderCancelVO;
import com.app.springpowpow.domain.OrderDTO;
import com.app.springpowpow.domain.OrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OrderCancelMapper {

    public void insert(OrderCancelVO orderCancelVO);

    public List<OrderCancelVO> selectAll();

    public Optional<OrderCancelVO> select(Long id);

    public void delete(Long id);

    public void update(OrderCancelVO orderCancelVO);

    public Optional<OrderCancelDTO> selectAllByDate(String orderCancelDate);
}
