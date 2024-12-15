package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.CardVO;
import org.apache.ibatis.annotations.Mapper;

import javax.smartcardio.Card;
import java.util.Optional;

@Mapper
public interface CardMapper {
// 카드 단일 조회
    public Optional<CardVO> select(Long id);

// 카드 추가
    public void insert(CardVO cardVO);

    //카드 취소
    public void delete(Long id);

    //카드 정보 업데이트
    public void update(CardVO cardVO);
}
