package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;


@Component
@Data
@Schema(description = "회원 정보")
public class OrderVO {
    @Schema(description = "주문 ID", example = "1", required = true)
    private Long id;

    @Schema(description = "상품 ID", example = "101", required = true)
    private Long productId;

    @Schema(description = "회원 ID", example = "501", required = true)
    private Long memberId;

    @Schema(description = "배송 ID", example = "301", required = true)
    private Long deliveryId;

    @Schema(description = "주문 번호", example = "1000100")
    private String orderNumber;

    @Schema(description = "주문 날짜", example = "2024-06-01")
    private String orderDate;

    @Schema(description = "주문 주소", example = "서울특별시 강남구 테헤란로 123")
    private String orderAddress;

    @Schema(description = "상품 개수", example = "3")
    private Integer productCount;

}
