package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@Schema(description = "상품 좋아요 정보")
public class ProductLikesVO implements Serializable {
//    @Schema(description = "회원 번호", example = "21", required = true)
//    private Long id;
    @Schema(description = "상품 좋아요 번호", example = "11", required = true)
    private Long id;
    @Schema(description = "제품 번호", example = "21", required = true)
    private Long productId;
    @Schema(description = "회원 번호", example = "2", required = true)
    private Long memberId;


}
