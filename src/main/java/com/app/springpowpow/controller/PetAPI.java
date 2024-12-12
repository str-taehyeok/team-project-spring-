package com.app.springpowpow.controller;

import com.app.springpowpow.domain.PetDTO;
import com.app.springpowpow.domain.PetVO;
import com.app.springpowpow.domain.PostVO;
import com.app.springpowpow.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/my-pet/*")
public class PetAPI {
    private final PetService petService;

//    마이펫 작성
    @Operation(summary = "마이펫 작성", description = "마이펫 작성 API")
    @ApiResponse(responseCode = "200", description = "마이펫 작성 완료")
    @PostMapping("write")
    public PetDTO write(@RequestBody PetVO petVO) {
        log.info("{}", petVO.toString());
        petService.write(petVO);
        Optional<PetDTO> foundPet = petService.getPet(petVO.getId());
        if(foundPet.isPresent()){
            return foundPet.get();
        }
        return new PetDTO();
    }

//    마이펫 전체 조회
@Operation(summary = "마이펫 전체 조회", description = "마이펫 정보를 전체 조회할 수 있는 API")
@Parameters({
        @Parameter( name = "id", description = "마이펫 번호", schema = @Schema(type="number"), in = ParameterIn.HEADER, required = true ),
        @Parameter( name = "petKind", description = "마이펫 종류", schema = @Schema(type="string"), in = ParameterIn.HEADER ),
        @Parameter( name = "petImage", description = "마이펫 이미지", schema = @Schema(type="string"), in = ParameterIn.HEADER),
        @Parameter( name = "petName", description = "마이펫 이름", schema = @Schema(type="string"), in = ParameterIn.HEADER ),
        @Parameter( name = "petGender", description = "마이펫 성별 ", schema = @Schema(type="boolean"), in = ParameterIn.HEADER, required = true ),
        @Parameter( name = "petBreed", description = "마이펫 품종", schema = @Schema(type="string"), in = ParameterIn.HEADER, required = true),
        @Parameter( name = "petBirth", description = "마이펫 생일", schema = @Schema(type="string"), in = ParameterIn.HEADER ),
        @Parameter( name = "petVet", description = "마이펫 동물병원", schema = @Schema(type="string"), in = ParameterIn.HEADER ),
        @Parameter( name = "petWeight", description = "마이펫 몸무게", schema = @Schema(type="double"), in = ParameterIn.HEADER ),
        @Parameter( name = "petNeuter", description = "마이펫 중성화", schema = @Schema(type="boolean"), in = ParameterIn.HEADER ),
        @Parameter( name = "petColor", description = "마이펫 색상", schema = @Schema(type="string"), in = ParameterIn.HEADER ),
        @Parameter( name = "MemberEmail", description = "회원 이메일", schema = @Schema(type="string"), in = ParameterIn.HEADER ),
        @Parameter( name = "MemberName", description = "회원 이름", schema = @Schema(type="string"), in = ParameterIn.HEADER )
})
@GetMapping("list")
public List<PetDTO> getPet(){
    return petService.getList();
}

//  마이펫 단일 조회
@Operation(summary = "마이펫 정보 조회", description = "마이펫 정보를 전체 조회할 수 있는 API")
@Parameter( name = "id", description = "마이펫 번호", schema = @Schema(type="number"), in = ParameterIn.HEADER, required = true )
@GetMapping("my-pet/{id}")
public PetDTO getPet(@PathVariable Long id){
    Optional<PetDTO> foundPost = petService.getPet(id);
    if(foundPost.isPresent()){
        return foundPost.get();
    }
    return new PetDTO();
}

// 마이펫 수정
@Operation(summary = "마이펫 수정", description = "마이펫 수정할 수 있는 API")
@Parameter( name = "id", description = "마이펫 번호", schema = @Schema(type="number"), in = ParameterIn.PATH, required = true )
@ApiResponse(responseCode = "200", description = "마이펫 수정 완료")
@PutMapping("my-pet/{id}")
public PetDTO modify(@PathVariable Long id, @RequestBody PetVO petVO){
    petVO.setId(id);
    petService.modify(petVO);
    Optional<PetDTO> foundPet = petService.getPet(petVO.getId());
    if(foundPet.isPresent()){
        return foundPet.get();
    }
    return new PetDTO();
}

    //    마이펫 삭제
    @Operation(summary = "마이펫 삭제", description = "마이펫 삭제할 수 있는 API")
    @Parameter( name = "id", description = "마이펫 번호", schema = @Schema(type="number"), in = ParameterIn.PATH, required = true )
    @ApiResponse(responseCode = "200", description = "마이펫 삭제 완료")
    @DeleteMapping("pet/{id}")
    public void delete(@PathVariable Long id){
        petService.remove(id);
    }


}
