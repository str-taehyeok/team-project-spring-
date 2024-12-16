package com.app.springpowpow.controller;

import com.app.springpowpow.domain.*;
import com.app.springpowpow.service.BannerService;
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

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/banners/*")
public class BannerAPI {
    private final BannerService bannerService;

    @Operation(summary = "이미지 업로드", description = "이미지를 저장할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "이미지 업로드 완료")
    @PostMapping("upload")
    public ResponseEntity<Map<String, String>> upload(
            @RequestParam("memberId") Long memberId,
            @RequestParam("bannerStart") String bannerStart,
            @RequestParam("bannerEnd") String bannerEnd,
            @RequestParam("bannerTitle") String bannerTitle,
            @RequestParam("bannerType") String bannerType,
            @RequestParam("bannerLink") String bannerLink,
            @RequestParam("uploadFile") MultipartFile uploadFile
    ) throws IOException {

        Map<String, String> response = new HashMap<>();
        String rootPath = "C:/upload/" + getPath();

        File file = new File(rootPath);
        if(!file.exists()){
            file.mkdirs();
        }

//        uuid 생성
        String uuid = UUID.randomUUID().toString();
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

    @Operation(summary = "배너 작성", description = "배너 새로 작성 할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "배너 작성 완료")
    @PostMapping("write")
    public ResponseEntity<Map<String, String>> insert(
            @RequestParam("uuid") String uuid,
            @RequestParam("memberId") Long memberId,
            @RequestParam("bannerStart") String bannerStart,
            @RequestParam("bannerEnd") String bannerEnd,
            @RequestParam("bannerTitle") String bannerTitle,
            @RequestParam("bannerType") String bannerType,
            @RequestParam("bannerLink") String bannerLink,
            @RequestParam("uploadFile") MultipartFile uploadFile
    ) {

        Map<String, String> response = new HashMap<>();

        BannerVO bannerVO = new BannerVO();
        bannerVO.setMemberId(memberId);
        bannerVO.setBannerStart(bannerStart);
        bannerVO.setBannerEnd(bannerEnd);
        bannerVO.setBannerTitle(bannerTitle);
        bannerVO.setBannerType(bannerType);
        bannerVO.setBannerLink(bannerLink);
        bannerVO.setBannerFileName(uuid + "_" + uploadFile.getOriginalFilename());
        bannerVO.setBannerFilePath(getPath());
        bannerService.write(bannerVO);

        response.put("message", "이미지 등록 완료");
        return ResponseEntity.ok(response);

    };

    @GetMapping("display")
    public byte[] display(String fileName) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("C:/upload/" + fileName));
    }

    //    현재 시간을 기준으로 년월일로 관리할 수 있게 경로를 붙인다.
    private String getPath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }



    //    배너 전체 조회
    @Operation(summary = "배너 전체 리스트", description = "배너 정보 전체 볼 수 있는 API")
    @Parameters({
            @Parameter(name = "id", description = "배너 번호", schema = @Schema(type = "number"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "bannerStart", description = "배너 등록일자", schema = @Schema(type = "string", format = "date"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "bannerEnd", description = "배너 종료일자", schema = @Schema(type = "string", format = "date"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "bannerTitle", description = "배너 제목", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "bannerType", description = "배너 타입", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "bannerImage", description = "배너 이미지", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true)
    })
    @GetMapping("list-all")
    public List<BannerDTO> getList() {
        return bannerService.getList();
    }

    //    배너 단일 조회
    @Operation(summary = "배너 단일 정보", description = "배너 단일 정보를 볼 수 있는 API")
    @Parameter( name = "id", description = "배너 번호", schema = @Schema(type="number"), in = ParameterIn.PATH, required = true )
    @GetMapping("list/{id}")
    public BannerDTO read(@PathVariable Long id){
        Optional<BannerDTO> foundPost = bannerService.read(id);
        if(foundPost.isPresent()){
            return foundPost.get();
        }
        return new BannerDTO();
    }

    // 배너 수정
    @Operation(summary = "배너 수정", description = "배너 수정할 수 있는 API")
    @Parameter( name = "id", description = "배너 번호", schema = @Schema(type="number"), in = ParameterIn.PATH, required = true )
    @ApiResponse(responseCode = "200", description = "배너 수정 완료")
    @PutMapping("update/{id}")
    public BannerDTO modify(@PathVariable Long id, @RequestBody BannerVO bannerVO){
        bannerVO.setId(id);
        bannerService.edit(bannerVO);
        Optional<BannerDTO> foundPet = bannerService.read(bannerVO.getId());

        if(foundPet.isPresent()){
            return foundPet.get();
        }
        return new BannerDTO();
    }

    //  배너 삭제
    @Operation(summary = "배너 삭제", description = "배너 삭제 할 수 있는 API")
    @Parameter( name = "id", description = "게시글 번호", schema = @Schema(type="number"), in = ParameterIn.PATH, required = true )
    @ApiResponse(responseCode = "200", description = "배너 삭제 완료")
    @DeleteMapping("list/{id}")
    public void delete(@PathVariable Long id){
        bannerService.remove(id);
    }

}
