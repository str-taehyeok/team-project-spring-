package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.PetDTO;
import com.app.springpowpow.domain.PetVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PetMapper {
//    마이펫 작성
    public void insert(PetVO petVO);

//    마이펫 전체 조회
    public List<PetDTO>selectAll();

//    마이펫 단일 조회
    public Optional<PetDTO> select(Long id);

//    마이펫 수정
    public void update(PetVO petVO);

//    마이펫 삭제
    public void delete(Long id);
}
