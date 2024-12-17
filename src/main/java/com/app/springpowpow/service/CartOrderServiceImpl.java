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
    public List<CartOrderDTO> getCartOrder(Long id) {
        return cartOrderDAO.getCartOrder(id);
    }

    @Override
    public Optional<CartOrderDTO> getCartOrderById(Long id) {
        return cartOrderDAO.getCartOrderById(id);
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
