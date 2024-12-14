package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
//@Schema(description = "회원 정보")
public class DeliveryVO{
    @Schema(description = "배송 ID", example = "1", required = true)
    private Long id;
    @Schema(description = "제품 ID", example = "1", required = true)
    private Long productId;
    @Schema(description = "배송 ID", example = "10000")
    private int deliveryFee;
    @Schema(description = "배송비 종류", example = "무료")
    private String deliveryFeeKind;
    @Schema(description = "무료배송 금액", example = "20000")
    private int deliveryFeeFree;
    @Schema(description = "배송사 선택", example = "일반택배")
    private String deliveryHow;
    @Schema(description = "착불여부 선택", example = "선결제")
    private String deliveryPayWhen;
    @Schema(description = "택배사 선택", example = "우체국 택배")
    private String deliveryCompany;
}
