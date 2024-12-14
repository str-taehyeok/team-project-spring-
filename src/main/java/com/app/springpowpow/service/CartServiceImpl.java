package com.app.springpowpow.service;

import com.app.springpowpow.domain.CartDTO;
import com.app.springpowpow.domain.CartVO;
import com.app.springpowpow.repository.CartDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class CartServiceImpl implements CartService {

    @Autowired
    private final CartDAO cartDAO;

    @Override
    public List<CartDTO> getCartById(Long memberId) {
        return cartDAO.getCartById(memberId);
    }

    @Override
    public Optional<CartDTO> getCartProductById(Long memberId) {
        return cartDAO.getCartProductById(memberId);
    }

    public void addCart(CartVO cartVO) {
        cartDAO.addCart(cartVO);
    }

    public void removeCart(CartVO cartVO) {
        cartDAO.removeCart(cartVO);
    }

    public void removeMember(Long memberId) {
        cartDAO.removeMember(memberId);
    }
}
