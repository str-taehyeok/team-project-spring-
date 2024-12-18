package com.app.springpowpow.service;

import com.app.springpowpow.domain.CartOrderDTO;
import com.app.springpowpow.repository.CartDAO;
import com.app.springpowpow.repository.CartOrderDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class CartOrderServiceImpl implements CartOrderService {

    @Autowired
    private final CartOrderDAO cartOrderDAO;

    @Override
    public List<CartOrderDTO> getCartOrder(Long memberId) {
        return cartOrderDAO.getCartOrder(memberId);
    }

    @Override
    public Optional<CartOrderDTO> getCartOrderById(Long memberId, Long productId) {
        return cartOrderDAO.getCartOrderById(memberId, productId);
    }

    @Override
    public void modify(CartOrderDTO cartOrderDTO) {
        cartOrderDAO.addCartOrder(cartOrderDTO);
    }

    @Override
    public void remove(Long id) {
        cartOrderDAO.removeCartOrder(id);
    }
}
