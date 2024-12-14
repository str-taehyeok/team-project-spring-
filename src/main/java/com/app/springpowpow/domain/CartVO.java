package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
//@Schema(description = "회원 정보")
public class CartVO {
//    @Schema(description = "회원 번호", example = "21", required = true)
//    private Long id;
    @Schema(description = "카트 번호", example = "1", required = true)
    private Long id;
    @Schema(description = "제품 번호", example = "1", required = true)
    private Long productId;
    @Schema(description = "회원 번호", example = "1", required = true)
    private Long memberId;

}
