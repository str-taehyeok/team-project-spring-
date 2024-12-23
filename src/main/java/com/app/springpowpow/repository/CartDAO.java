package com.app.springpowpow.repository;

import com.app.springpowpow.domain.CartDTO;
import com.app.springpowpow.domain.CartVO;
import com.app.springpowpow.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CartDAO {

    private final CartMapper cartMapper;

//    전체 조회
    public List<CartDTO> getCartById(Long memberId) {
        return cartMapper.selectAll(memberId);
    }

//    단일 조회
    public Optional<CartDTO> getCartProductById(Long memberId) {
        return cartMapper.select(memberId);
    }

    public void addCart(CartVO cartVO) {
        cartMapper.insert(cartVO);
    }

    public void removeCart(CartVO cartVO) {
        cartMapper.delete(cartVO);
    }

// 회원 탈퇴시 카트 바구니에서 회원 자체를 드롭
    public void removeMember(Long memberId) {
        cartMapper.deleteAll(memberId);
    }

}
