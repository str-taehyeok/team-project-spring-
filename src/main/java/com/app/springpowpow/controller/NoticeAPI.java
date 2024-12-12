package com.app.springpowpow.controller;

import com.app.springpowpow.domain.NoticeDTO;
import com.app.springpowpow.domain.NoticeVO;
import com.app.springpowpow.domain.PetDTO;
import com.app.springpowpow.domain.PetVO;
import com.app.springpowpow.service.NoticeService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
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
@RequestMapping("/notice/*")
public class NoticeAPI {

    private final NoticeService noticeService;

    //    공지사항 작성
    @Operation(summary = "공지사항 작성", description = "공지사항 새로 작성 할 수 있는 API")
//    @Parameters({
//            @Parameter(name = "id", description = "공지사항 번호", schema = @Schema(type = "number"), in = ParameterIn.HEADER, required = true),
//            @Parameter(name = "noticeTitle", description = "공지사항 제목", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
//            @Parameter(name = "noticeContent", description = "공지사항 내용", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
//            @Parameter(name = "noticeDate", description = "공지사항 등록일자", schema = @Schema(type = "string", format = "date"), in = ParameterIn.HEADER, required = true)
//    })
    @ApiResponse(responseCode = "200", description = "공지사항 작성 완료")
    @PostMapping("write")
    public NoticeDTO write(@RequestBody NoticeVO noticeVO) {
        log.info("{}", noticeVO.toString());
        noticeService.write(noticeVO);
        Optional<NoticeDTO> foundNotice = noticeService.read(noticeVO.getId());
        if(foundNotice.isPresent()){
            return foundNotice.get();
        }
        return new NoticeDTO();
    }
//    공지사항 전체 조회
    @Operation(summary = "공지사항 전체 리스트", description = "공지사항 정보 전체 볼 수 있는 API")
    @Parameters({
            @Parameter(name = "id", description = "공지사항 번호", schema = @Schema(type = "number"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "noticeTitle", description = "공지사항 제목", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "noticeContent", description = "공지사항 내용", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "noticeDate", description = "공지사항 등록일자", schema = @Schema(type = "string", format = "date"), in = ParameterIn.HEADER, required = true)
    })
    @GetMapping("notices")
    public List<NoticeDTO> getList() {
        return noticeService.getList();
    }

    //    공지사항 단일 조회
    @Operation(summary = "공지사항 단일 정보", description = "공지사항 단일 정보를 볼 수 있는 API")
    @Parameter( name = "id", description = "공지사항 번호", schema = @Schema(type="number"), in = ParameterIn.PATH, required = true )
    @GetMapping("notice/{id}")
    public NoticeDTO getPet(@PathVariable Long id){
        Optional<NoticeDTO> foundPost = noticeService.read(id);
        if(foundPost.isPresent()){
            return foundPost.get();
        }
        return new NoticeDTO();
    }

    // 공지사항 수정
    @Operation(summary = "공지사항 수정", description = "공지사항 수정할 수 있는 API")
    @Parameter( name = "id", description = "공지사항 번호", schema = @Schema(type="number"), in = ParameterIn.PATH, required = true )
    @ApiResponse(responseCode = "200", description = "공지사항 수정 완료")
    @PutMapping("notice/{id}")
    public NoticeDTO modify(@PathVariable Long id, @RequestBody NoticeVO noticeVO){
        noticeVO.setId(id);
        noticeService.edit(noticeVO);
        Optional<NoticeDTO> foundPet = noticeService.read(noticeVO.getId());
        if(foundPet.isPresent()){
            return foundPet.get();
        }
        return new NoticeDTO();
    }

//  공지사항 삭제
    @Operation(summary = "공지사항 삭제", description = "공지사항 삭제 할 수 있는 API")
    @Parameter( name = "id", description = "게시글 번호", schema = @Schema(type="number"), in = ParameterIn.PATH, required = true )
    @ApiResponse(responseCode = "200", description = "공지사항 삭제 완료")
    @DeleteMapping("notice/{id}")
    public void delete(@PathVariable Long id){
        noticeService.remove(id);
    }

}
