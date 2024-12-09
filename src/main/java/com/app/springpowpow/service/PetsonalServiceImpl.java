package com.app.springpowpow.service;

import com.app.springpowpow.domain.PetsonalDTO;
import com.app.springpowpow.domain.PetsonalVO;
import com.app.springpowpow.repository.PetsonalDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class PetsonalServiceImpl implements PetsonalService {

    private final PetsonalDAO petsonalDAO;

    @Override
    public void registerSurvey(PetsonalVO petsonalVO) {
        petsonalDAO.save(petsonalVO);
    }

    @Override
    public Optional<PetsonalDTO> getSurveyByPetId(Long petId) {
        return petsonalDAO.findById(petId);
    }


}
