package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@Schema(description = "주문 취소 정보")
public class OrderCancelVO{
    @Schema(description = "주문 취소 번호", example = "1", required = true)
    private Long id;
    @Schema(description = "상품 번호", example = "1", required = true)
    private Long productId;
    @Schema(description = "멤버 번호", example = "1", required = true)
    private Long memberId
            ;
    private Long orderId;
    @Schema(description = "주문 취소 날짜", example = "2024-12-16", required = true)
    private Long orderCancelDate;
    @Schema(description = "주문 취소 개수", example = "1", required = true)
    private Long orderCancelCount;

}
