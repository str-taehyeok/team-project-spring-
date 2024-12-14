package com.app.springpowpow.controller;

import com.app.springpowpow.domain.PetsonalDTO;
import com.app.springpowpow.domain.PetsonalVO;
import com.app.springpowpow.service.PetsonalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/petsonal/*")
public class PetsonalAPI {

    private final PetsonalService petsonalService;


    //    설문결과 등록
    @Operation(summary = "설문결과등록", description = "설문지 결과를 등록할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "설문지 등록 완료")
    @PostMapping("register-survey")
    public PetsonalDTO registerSurvey(@RequestBody PetsonalVO petsonalVO){

        petsonalService.registerSurvey(petsonalVO);
        Optional<PetsonalDTO> foundSurveyResult = petsonalService.getSurveyByPetId(petsonalVO.getPetId());

        if(foundSurveyResult.isPresent()){
            return foundSurveyResult.get();
        }
        return new PetsonalDTO();
    }

    //    설문결과 조회
    @Operation(summary = "설문결과 조회", description = "설문결과를 조회할 수 있는 API")
    @Parameter( name = "petId", description = "펫 번호", schema = @Schema(type="number"), in = ParameterIn.PATH, required = true )
    @GetMapping("result/{petId}")
    public PetsonalDTO getPost(@PathVariable Long petId){
        Optional<PetsonalDTO> foundSurveyResult = petsonalService.getSurveyByPetId(petId);
        if(foundSurveyResult.isPresent()){
            return foundSurveyResult.get();
        }
        return new PetsonalDTO();
    }

    //    설문결과 삭제
    @Operation(summary = "설문결과 삭제", description = "설문결과를 삭제할 수 있는 API")
    @Parameter( name = "id", description = "설문결과 번호", schema = @Schema(type="number"), in = ParameterIn.PATH, required = true )
    @ApiResponse(responseCode = "200", description = "설문결과 삭제 완료")
    @DeleteMapping("petsonal/{id}")
    public void delete(@PathVariable Long id){
        petsonalService.deleteSurveyResult(id);
    }


}
