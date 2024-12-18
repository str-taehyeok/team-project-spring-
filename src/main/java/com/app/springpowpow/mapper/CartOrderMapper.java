package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.CartOrderDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CartOrderMapper {

    public List<CartOrderDTO> selectAll(Long memberId);

    public Optional<CartOrderDTO> select(@Param("memberId") Long memberId, @Param("productId") Long productId);

    public void update(CartOrderDTO cartOrderDTO);

    public void delete(Long id);
}
