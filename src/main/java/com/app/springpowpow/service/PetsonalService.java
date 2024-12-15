package com.app.springpowpow.service;

import com.app.springpowpow.domain.PetVO;
import com.app.springpowpow.domain.PetsonalDTO;
import com.app.springpowpow.domain.PetsonalVO;

import java.util.Optional;

public interface PetsonalService {

    public void registerSurvey(PetsonalVO petsonalVO, PetVO petVO);

    public Optional<PetsonalDTO> getSurveyByPetId(Long petId);

    public void deleteSurveyResult(Long id);
}
