package com.app.springpowpow.repository;

import com.app.springpowpow.domain.CardVO;
import com.app.springpowpow.mapper.CardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CardDAO {

    private final CardMapper cardMapper;

    public void save(CardVO cardVO) {
        cardMapper.insert(cardVO);
    }

    public Optional<CardVO> findById(Long id) {
        return cardMapper.select(id);
    }

    public void update(CardVO cardVO) {
        cardMapper.update(cardVO);
    }

    public void delete(Long id) {
        cardMapper.delete(id);
    }
}
