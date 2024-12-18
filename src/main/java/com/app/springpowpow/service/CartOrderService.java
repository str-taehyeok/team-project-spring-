package com.app.springpowpow.service;

import com.app.springpowpow.domain.CartOrderDTO;
import com.app.springpowpow.repository.CartOrderDAO;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CartOrderService {

    public List<CartOrderDTO> getCartOrder(Long memberId);

    public Optional<CartOrderDTO> getCartOrderById(Long memberId, Long productId);

    public void modify(CartOrderDTO cartOrderDTO);

    public void remove(Long id);
}
