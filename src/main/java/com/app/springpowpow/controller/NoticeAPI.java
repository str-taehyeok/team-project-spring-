package com.app.springpowpow.controller;

import com.app.springpowpow.domain.NoticeVO;
import com.app.springpowpow.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/notice/*")
public class NoticeAPI {

    private final NoticeService noticeService;


//    공지사항 작성
    @Operation(summary = "공지사항 작성", description = "공지사항 새로 작성 할 수 있는 API")
    @Parameters({
            @Parameter(name = "id", description = "공지사항 번호", schema = @Schema(type = "number"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "noticeTitle", description = "공지사항 제목", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "noticeContent", description = "공지사항 내용", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "noticeDate", description = "공지사항 등록일자", schema = @Schema(type = "string", format = "date"), in = ParameterIn.HEADER, required = true)
    })
    @ApiResponse(responseCode = "200", description = "공지사항 작성 완료")
    @PostMapping("write")
    public NoticeVO write(@RequestBody NoticeVO noticeVO) {
        noticeService.write(noticeVO);
        return new NoticeVO();
    }





}
