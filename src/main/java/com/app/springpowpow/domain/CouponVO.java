package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@Schema(description = "쿠폰 정보")
public class CouponVO {

    @Schema(description = "쿠폰 번호", example = "1", required = true)
    private Long id;
    @Schema(description = "멤버 번호", example = "1")
    private Long memberId;
    @Schema(description = "제품 번호", example = "1")
    private Long productId;
    @Schema(description = "쿠폰 이름", example = "쿠폰 이름", required = true)
    private String couponTitle;
    @Schema(description = "쿠폰 카테고리", example = "장난감", required = true)
    private String couponCategory;
    @Schema(description = "쿠폰 카테고리 동물", example = "고양이", required = true)
    private String couponAnimal;
    @Schema(description = "쿠폰 코드", example = "ABCDEFGH1234125", required = true)
    private String couponCode;
    @Schema(description = "쿠폰 시작 시간", example = "2024-11-21")
    private String couponStart;
    @Schema(description = "쿠폰 종료 시간", example = "2024-12-21")
    private String couponEnd;
    @Schema(description = "쿠폰 내용", example = "이것은 쿠폰내용", required = true)
    private String couponContent;
    @Schema(description = "쿠폰 수량", example = "44")
    private Integer couponQuantity;
    @Schema(description = "쿠폰 할인율", example = "20")
    private Integer couponDiscountRate;

}
