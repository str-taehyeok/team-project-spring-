package com.app.springpowpow.repository;

import com.app.springpowpow.domain.CartDTO;
import com.app.springpowpow.domain.CartOrderDTO;
import com.app.springpowpow.mapper.CartOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CartOrderDAO {

    private final CartOrderMapper cartOrderMapper;

    public List<CartOrderDTO> getCartOrder(Long memberId) {
        return cartOrderMapper.selectAll(memberId);
    }

    public Optional<CartOrderDTO> getCartOrderById(Long memberId, Long productId) {
        return cartOrderMapper.select(memberId, productId);
    }

    public void addCartOrder(CartOrderDTO cartOrderDTO) {
        cartOrderMapper.update(cartOrderDTO);
    }

    public void removeCartOrder(Long id) {
        cartOrderMapper.delete(id);
    }
}
