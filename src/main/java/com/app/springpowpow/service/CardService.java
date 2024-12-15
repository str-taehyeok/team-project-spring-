package com.app.springpowpow.service;

import com.app.springpowpow.domain.CardVO;

import java.util.Optional;

public interface CardService {

    public void write(CardVO cardVO);

    public Optional<CardVO> getCard(Long id);

    public void modify(CardVO cardVO);

    public void remove(Long id);
}
