package com.app.springpowpow.service;

import com.app.springpowpow.domain.CartDTO;
import com.app.springpowpow.domain.CartVO;

import java.util.List;
import java.util.Optional;

public interface CartService {

    public List<CartDTO> getCartById(Long memberId);

    public Optional<CartDTO> getCartProductById(Long memberId);

    public void addCart(CartVO cartVO);

    public void removeCart(CartVO cartVO);

    // 회원 탈퇴시 카트 바구니에서 회원 자체를 드롭
    public void removeMember(Long memberId);
}
