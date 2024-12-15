package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
@Data
@Schema(description = "공지사항 정보")
public class NoticeVO {

    @Schema(description = "공지사항 번호", example = "1", required = true)
    private Long id;
    @Schema(description = "관리자 정보", example = "1", required = true)
    private Long memberId;
    @Schema(description = "공지사항 제목", example = "POWPOW 공지사항 제목", required = true)
    private String noticeTitle;
    @Schema(description = "공지사항 내용", example = "POWPOW 공지사항 내용", required = true)
    private String noticeContent;
    @Schema(description = "공지사항 등록일자", example = "2012-11-07")
    private String noticeDate;
    @Schema(description = "공지사항 조회수", example = "0")
    private Long noticeCount;
    
}
