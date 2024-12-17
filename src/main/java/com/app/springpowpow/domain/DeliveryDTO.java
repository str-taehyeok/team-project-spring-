package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
//@Schema(description = "회원 정보")
public class DeliveryDTO {
    @Schema(description = "배송 ID", example = "1", required = true)
    private Long id;
    @Schema(description = "제품 ID", example = "1", required = true)
    private Long productId;
    @Schema(description = "배송 가격", example = "10000")
    private int deliveryFee;
    @Schema(description = "배송비 종류", example = "free")
    private String deliveryFeeKind;
    @Schema(description = "무료배송 금액", example = "20000")
    private int deliveryFeeFree;
    @Schema(description = "배송사 선택", example = "normalDelivery")
    private String deliveryHow;
    @Schema(description = "착불여부 선택", example = "prePay")
    private String deliveryPayWhen;
    @Schema(description = "택배사 선택", example = "lotte")
    private String deliveryCompany;
//  총 가격
    private int totalPrice;
//  배송상태
    private String deliveryStatus;

}
