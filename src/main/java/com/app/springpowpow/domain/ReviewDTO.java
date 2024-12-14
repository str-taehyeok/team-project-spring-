package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
//@Schema(description = "회원 정보")
public class ReviewDTO {
    @Schema(description = "리뷰 번호", example = "1", required = true)
    private Long id;

    @Schema(description = "상품 번호", example = "101", required = true)
    private Long productId;

    @Schema(description = "회원 번호", example = "21", required = true)
    private Long memberId;

    @Schema(description = "리뷰 별점", example = "5", required = true)
    private int reviewStar;

    @Schema(description = "리뷰 작성 날짜", example = "2024-06-14T15:30:00")
    private String reviewDate;

    @Schema(description = "리뷰 내용", example = "정말 만족스러운 상품입니다!", required = true)
    private String reviewContent;

    @Schema(description = "훠원사진", example = "profile.png!")
    private String memberImage;

    @Schema(description = "회원 닉네임", example = "징징이")
    private String memberNickname;

}
