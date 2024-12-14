package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.CartDTO;
import com.app.springpowpow.domain.CartVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CartMapper {

    public List<CartDTO> selectAll(Long memberId);

    public Optional<CartDTO> select(Long memberId);

    public void insert(CartVO cartVO);

    public void delete(CartVO cartVO);

    //회원 탈퇴시 카트 바구니에서 회원 자체를 드롭

    public void deleteAll(Long memberId);
}
