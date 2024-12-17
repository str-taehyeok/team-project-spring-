package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CartOrderDTO {

    @Schema(description = "주문 아이디", example = "1", required = true)
    private Long id;
    @Schema(description = "제품 아이디", example = "1", required = true)
    private Long productId;
    @Schema(description = "회원 아이디", example = "1", required = true)
    private Long memberId;
    @Schema(description = "각 제품 담은 수량", example = "2", required = true)
    private int cartProductCount;
    @Schema(description = "제품 이름", example = "울 애기 쌩쌩 사료", required = true)
    private String productName;
    @Schema(description = "제품 이미지 이름", example = "image.jpg", required = true)
    private String productFileName;
    @Schema(description = "제품 파일 경로", example = "", required = true)
    private String productFilePath;
    @Schema(description = "제품 원가격", example = "5000", required = true)
    private String productRealPrice;
    @Schema(description = "제품 할인가", example = "4500")
    private String productPrice;
    @Schema(description = "제품 재고", example = "1", required = true)
    private String productStock;
    @Schema(description = "쿠폰 이름", example = "5% 할인 쿠폰", required = true)
    private String couponTitle;




}
