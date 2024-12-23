package com.app.springpowpow.controller;

import com.app.springpowpow.domain.*;
import com.app.springpowpow.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/my-pet/*")
public class PetAPI {
    private final PetService petService;
    private final PetVO petVO;

    @Operation(summary = "이미지 업로드", description = "이미지를 저장하는 API")
    @ApiResponse(responseCode = "200", description = "이미지 업로드 완료")
    @PostMapping("upload")
    public ResponseEntity<Map<String, String>> upload(
            @RequestParam("memberId") Long memberId,
            @RequestParam("petName") String petName,
            @RequestParam("petKind") String petKind,
            @RequestParam("petGender") String petGender,
            @RequestParam("petBreed") String petBreed,
            @RequestParam("petBirth") String petBirth,
            @RequestParam("petVet") String petVet,
            @RequestParam("petWeight") double petWeight,
            @RequestParam("petNeuter") String petNeuter,
            @RequestParam("uploadFile") MultipartFile uploadFile
    ) throws IOException {

        Map<String, String> response = new HashMap<>();
        String rootPath = "C:/upload/" + getPath();

        File file = new File(rootPath);
        if (!file.exists()) {
            file.mkdirs();
        }

        //        uuid 생성
        String uuid = UUID.randomUUID().toString();
        log.info("Generated UUID: {}", uuid);
        uploadFile.transferTo(new File(rootPath, uuid + "_" + uploadFile.getOriginalFilename()));

//        썸네일
        if(uploadFile.getContentType().startsWith("image")){
            FileOutputStream out = new FileOutputStream(new File(rootPath, "t_" + uuid + "_" + uploadFile.getOriginalFilename()));
            Thumbnailator.createThumbnail(uploadFile.getInputStream(), out, 100, 100);
            out.close();
        }

        log.info("upload path : {}", uuid.toString());

        response.put("uuid", uuid);
        return ResponseEntity.ok(response);
    }

    // MyPet 작성 API
    @Operation(summary = "마이펫 작성", description = "마이펫 작성 API")
    @ApiResponse(responseCode = "200", description = "마이펫 작성 완료")
    @PostMapping("write")
    public ResponseEntity<Map<String, String>> write(
            @RequestParam("uuid") String uuid,
            @RequestParam("uploadFile") MultipartFile uploadFile,
            @RequestParam("memberId") Long memberId,
            @RequestParam("petName") String petName,
            @RequestParam("petKind") String petKind,
            @RequestParam("petGender") String petGender,
            @RequestParam("petBreed") String petBreed,
            @RequestParam("petBirth") String petBirth,
            @RequestParam("petVet") String petVet,
            @RequestParam("petWeight") double petWeight,
            @RequestParam("petNeuter") String petNeuter
    ) {
        Map<String, String> response = new HashMap<>();
        PetVO petVO = new PetVO();
        petVO.setMemberId(memberId);
        petVO.setPetName(petName);
        petVO.setPetKind(petKind);
        petVO.setPetGender(petGender);
        petVO.setPetBreed(petBreed);
        petVO.setPetBirth(petBirth);
        petVO.setPetVet(petVet);
        petVO.setPetWeight(petWeight);
        petVO.setPetNeuter(petNeuter);
        petVO.setPetFileName(uuid + "_" + uploadFile.getOriginalFilename());
        petVO.setPetFilePath(getPath());
        petService.write(petVO);

        response.put("message", "마이펫 등록 완료");
        return ResponseEntity.ok(response);
    }

    @GetMapping("display")
    public byte[] display(String fileName) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("C:/upload/" + fileName));
    }

    //    현재 시간을 기준으로 년월일로 관리할 수 있게 경로를 붙인다.
    private String getPath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
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
    @GetMapping("list/{id}")
    public List<PetDTO> getPetList(@PathVariable Long id){
        return petService.getList(id);
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
    @PutMapping("petEdit")
    public ResponseEntity<Map<String, String>> petEdit(
        @RequestParam("memberId") Long memberId,
        @RequestParam("petKind") String petKind,
        @RequestParam("petFilePath") String petFilePath,
        @RequestParam("petFileName") String petFileName,
        @RequestParam("petName") String petName,
        @RequestParam("petGender") String petGender,
        @RequestParam("petBreed") String petBreed,
        @RequestParam("petBirth") String petBirth,
        @RequestParam("petVet") String petVet,
        @RequestParam("petWeight") double petWeight,
        @RequestParam("petNeuter") String petNeuter,
        @RequestParam("uuid") String uuid,
        @RequestParam("uploadFile") MultipartFile uploadFile

    ){
        Map<String, String> response = new HashMap<>();
        PetVO petVO = new PetVO();
        petVO.setPetKind(petKind);
        petVO.setPetName(petName);
        petVO.setPetGender(petGender);
        petVO.setPetBreed(petBreed);
        petVO.setPetBirth(petBirth);
        petVO.setPetVet(petVet);
        petVO.setMemberId(memberId);
        petVO.setPetWeight(petWeight);
        petVO.setPetNeuter(petNeuter);
        if(!uuid.equals("")){
            petVO.setPetFilePath(getPath());
            petVO.setPetFileName(uuid + "_" + uploadFile.getOriginalFilename());
        }
        response.put("message", "마이펫 정보 수정 완료");

        petService.modify(petVO);
        log.info(petVO.toString());
        return ResponseEntity.ok(response);
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
