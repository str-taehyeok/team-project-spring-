package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.CartOrderDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CartOrderMapper {

    public List<CartOrderDTO> selectAll(Long id);

    public Optional<CartOrderDTO> select(Long id);

    public void update(CartOrderDTO cartOrderDTO);

    public void delete(Long id);
}
