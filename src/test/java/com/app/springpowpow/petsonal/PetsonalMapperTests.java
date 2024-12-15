package com.app.springpowpow.petsonal;

import com.app.springpowpow.domain.PetsonalDTO;
import com.app.springpowpow.domain.PetsonalVO;
import com.app.springpowpow.service.PetsonalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PetsonalMapperTests {

    @Autowired
    private PetsonalService petsonalService;


//    @Test
//    public void registerPetsonalTest() {
//        PetsonalVO petsonalVO = new PetsonalVO();
//
//        petsonalVO.setPetId(5L);
//        petsonalVO.setPetsonalActive(20);
//        petsonalVO.setPetsonalBrave(20);
//        petsonalVO.setPetsonalCalm(20);
//        petsonalVO.setPetsonalChic(20);
//        petsonalVO.setPetsonalCoward(20);
//        petsonalVO.setPetsonalDiligent(20);
//        petsonalVO.setPetsonalCute(20);
//        petsonalVO.setPetsonalLazy(20);
//
//        petsonalService.registerSurvey(petsonalVO);
//    }

    @Test
    public void getPetsonalTest() {
        petsonalService.getSurveyByPetId(10L).map(PetsonalDTO::toString).ifPresent(log::info);
    }

    @Test
    public void deletePetsonalTest() {
        petsonalService.deleteSurveyResult(2L);
    }

}
