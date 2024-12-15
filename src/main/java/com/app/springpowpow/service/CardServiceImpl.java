package com.app.springpowpow.service;

import com.app.springpowpow.domain.CardVO;
import com.app.springpowpow.repository.CardDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CardServiceImpl implements CardService {

    private final CardDAO cardDAO;

    @Override
    public void write(CardVO cardVO) {
        cardDAO.save(cardVO);
    }

    @Override
    public Optional<CardVO> getCard(Long id) {

        return cardDAO.findById(id);
    }

    @Override
    public void modify(CardVO cardVO) {
        cardDAO.update(cardVO);
    }

    @Override
    public void remove(Long id) {
        cardDAO.delete(id);
    }
}
