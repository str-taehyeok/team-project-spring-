package com.app.springpowpow.pet;

import com.app.springpowpow.domain.PetDTO;
import com.app.springpowpow.domain.PetVO;
import com.app.springpowpow.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PetMapperTests {

    @Autowired
    private PetService petService;

    @Test
    public void updatePetColorTest(){
        PetVO petVO = new PetVO();

        petVO.setId(12L);
        petVO.setPetColor("Orange");
        petService.modifyPetColor(petVO);

    }

    @Test
    public void selectPetByIdTest(){
        petService.getPet(1L).map(PetDTO::toString).ifPresent(log::info);
    }

}
