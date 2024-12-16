package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "주문 취소 정보")
public class OrderCancelDTO {
    @Schema(description = "주문 취소 번호", example = "1", required = true)
    private Long id;
    @Schema(description = "상품 번호", example = "1", required = true)
    private Long productId;
    @Schema(description = "멤버 번호", example = "1", required = true)
    private Long memberId;
    @Schema(description = "주문 취소 날짜", example = "2024-12-16", required = true)
    private String orderCancelDate;
    @Schema(description = "주문 취소 개수", example = "1", required = true)
    private int orderCancelCount;
    @Schema(description = "주문 취소 총 개수", example = "1")
    private int totalCancelCount;
    @Schema(description = "주문 취소 총 가격", example = "1")
    private int totalCancelPrice;
}
