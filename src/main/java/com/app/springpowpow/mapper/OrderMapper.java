package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.OrderDTO;
import com.app.springpowpow.domain.OrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OrderMapper {

    public void insert(OrderVO orderVO);

    public List<OrderDTO> selectAll();

    public Optional<OrderDTO> select(Long id);

    public void delete(Long id);

    public void update(OrderVO orderVO);

    public List<OrderVO> selectByMemberId(Long memberId);

    public Optional<OrderDTO> selectAllByDate(String orderDate);
}
