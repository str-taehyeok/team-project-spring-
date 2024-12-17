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


//    마이펫 작성
    @Operation(summary = "마이펫 작성", description = "마이펫 작성 API")
    @ApiResponse(responseCode = "200", description = "마이펫 작성 완료")
    @PostMapping("write")
    public ResponseEntity<Map<String, String>> write(
            @RequestParam("memberId") Long memberId,
            @RequestParam("petName") String petName,
            @RequestParam("petKind") String petKind,
            @RequestParam("petGender") String petGender,
            @RequestParam("petBreed") String petBreed,
            @RequestParam("petBirth") String petBirth,
            @RequestParam("petVet") String petVet,
            @RequestParam("petWeight") double petWeight,
            @RequestParam("petNeuter") String petNeuter,
            @RequestParam("uploadFileName") String uploadFileName,
            @RequestParam("uploadFilePath") String uploadFilePath
    ) {
        Map<String, String> response = new HashMap<>();

        // PetVO 생성 및 저장
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
        petVO.setPetFileName(uploadFileName);
        petVO.setPetFilePath(uploadFilePath);

        petService.write(petVO);

        log.info("Pet saved: {}", petVO);

        response.put("message", "마이펫 등록 완료");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "이미지 업로드", description = "이미지를 저장하는 API")
    @ApiResponse(responseCode = "200", description = "이미지 업로드 완료")
    @PostMapping("upload")
    public ResponseEntity<Map<String, String>> upload(@RequestParam("uploadFile") MultipartFile uploadFile) throws IOException {
        Map<String, String> response = new HashMap<>();

        // 파일 저장 및 썸네일 생성
        String uuid = saveFile(uploadFile);
        String uploadFilePath = getPath();
        String uploadFileName = uuid + "_" + uploadFile.getOriginalFilename();

        response.put("message", "이미지 업로드 완료");
        response.put("uploadFileName", uploadFileName);
        response.put("uploadFilePath", uploadFilePath);
        response.put("uuid", uuid);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "이미지 표시", description = "이미지를 표시하는 API")
    @GetMapping("display")
    public byte[] display(@RequestParam("fileName") String fileName) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("C:/upload/" + fileName));
    }

    private String saveFile(MultipartFile uploadFile) throws IOException {
        String rootPath = "C:/upload/" + getPath();
        File file = new File(rootPath);

        if (!file.exists()) {
            file.mkdirs();
        }

        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + "_" + uploadFile.getOriginalFilename();

        // 파일 저장
        uploadFile.transferTo(new File(rootPath, fileName));

        // 썸네일 생성 (이미지인 경우)
        if (uploadFile.getContentType().startsWith("image")) {
            try (FileOutputStream out = new FileOutputStream(new File(rootPath, "t_" + fileName))) {
                Thumbnailator.createThumbnail(uploadFile.getInputStream(), out, 100, 100);
            }
        }

        log.info("File saved: {}", fileName);
        return uuid;
    }

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
    @PutMapping("my-pet/{id}")
    public PetDTO modify(@PathVariable Long id, @RequestBody PetVO petVO) {
        petVO.setId(id);
        petService.modify(petVO);
        Optional<PetDTO> foundPet = petService.getPet(petVO.getId());
        if (foundPet.isPresent()) {
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
