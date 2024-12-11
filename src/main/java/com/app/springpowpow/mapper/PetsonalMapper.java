package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.PetsonalDTO;
import com.app.springpowpow.domain.PetsonalVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface PetsonalMapper {
    public void insert(PetsonalVO petsonalVO);

    public Optional<PetsonalDTO> select(Long petId);
}
