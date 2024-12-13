package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@Schema(description = "배너 정보")
//@Schema(description = "회원 정보")
public class BannerVO {

    @Schema(description = "배너 번호", example = "1", required = true)
    private Long id;
    @Schema(description = "관리자 정보", example = "1", required = true)
    private Long memberId;
    @Schema(description = "배너 제목", example = "POWPOW 공지사항 제목", required = true)
    private String bannerTitle;
    @Schema(description = "배너 타입", example = "POWPOW 공지사항 내용", required = true)
    private String bannerType;
    @Schema(description = "배너 등록일자", example = "2012-11-07")
    private String bannerStart;
    @Schema(description = "배너 종료일자", example = "2012-12-24")
    private String bannerEnd;
    @Schema(description = "배너 이미지", example = "powpow.jpg")
    private String bannerImage;

}
