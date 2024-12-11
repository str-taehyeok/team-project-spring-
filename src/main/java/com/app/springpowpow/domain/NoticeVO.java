package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
@Data
@Schema(description = "공지사항 정보")
public class NoticeVO implements Serializable {

    @Schema(description = "공지사항 번호", example = "25", required = true)
    private Long id;
    @Schema(description = "공지사항 제목", example = "POWPOW 공지사항 제목", required = true)
    private String noticeTitle;
    @Schema(description = "공지사항 내용", example = "POWPOW 공지사항 내용", required = true)
    private String noticeContent;
    @Schema(description = "공지사항 등록일자", example = "2024-12-11", required = true)
    private Date noticeDate;
    @Schema(description = "공지사항 조회수", example = "1000")
    private Long noticeCount;
    
}
