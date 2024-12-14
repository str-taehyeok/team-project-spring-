package com.app.springpowpow.repository;

import com.app.springpowpow.domain.PetsonalDTO;
import com.app.springpowpow.domain.PetsonalVO;
import com.app.springpowpow.mapper.PetsonalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PetsonalDAO {

    private final PetsonalMapper petsonalMapper;

    public void save(PetsonalVO petsonalVO) {
        petsonalMapper.insert(petsonalVO);
    }

    public Optional<PetsonalDTO> findById(Long id) {
        return petsonalMapper.select(id);
    }

    public void deleteSurveyResult(Long id){
        petsonalMapper.delete(id);
    }
}
