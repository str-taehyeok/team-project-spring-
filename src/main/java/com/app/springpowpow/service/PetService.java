package com.app.springpowpow.service;

import com.app.springpowpow.domain.PetDTO;
import com.app.springpowpow.domain.PetVO;

import java.util.List;
import java.util.Optional;

public interface PetService {
//    마이펫 작성
    public void write(PetVO petVO);
//    마이펫 전체 조회
    public List<PetDTO> getList();

//    마이펫 단일 조회
    public Optional<PetDTO> getPet(Long id);

//    마이펫 수정
    public void modify(PetVO petVO);

//    마이펫 삭제
    public void remove(Long id);
}
