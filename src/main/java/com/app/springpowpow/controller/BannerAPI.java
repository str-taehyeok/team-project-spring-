package com.app.springpowpow.controller;

import com.app.springpowpow.domain.BannerDTO;
import com.app.springpowpow.domain.BannerVO;
import com.app.springpowpow.domain.NoticeDTO;
import com.app.springpowpow.domain.NoticeVO;
import com.app.springpowpow.service.BannerService;
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

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/banners/*")
public class BannerAPI {
    private final BannerService bannerService;

    //    배너 작성
    @Operation(summary = "배너 작성", description = "배너 새로 작성 할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "배너 작성 완료")
    @PostMapping("write")
    public BannerDTO write(@RequestBody BannerVO bannerVO) {
        log.info("{}", bannerVO.toString());
        bannerService.write(bannerVO);
        Optional<BannerDTO> foundBanner = bannerService.read(bannerVO.getId());
        if(foundBanner.isPresent()){
            return foundBanner.get();
        }
        return new BannerDTO();
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
