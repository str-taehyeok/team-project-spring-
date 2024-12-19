package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@Schema(description = "제품 정보")
public class ProductVO{
    @Schema(description = "제품 번호", example = "1", required = true)
    private Long id;
    @Schema(description = "회원 번호", example = "21", required = true)
    private Long memberId;
    @Schema(description = "제품명", example = "덴탈 치약")
    private String productName;
    @Schema(description = "제품가격", example = "25000")
    private Integer productPrice;
    @Schema(description = "제품가격", example = "25000")
    private Integer productRealPrice;
    @Schema(description = "제품 코드", example = "000000")
    private String productCode;
    @Schema(description = "제품 등록일자", example = "2024-01-01")
    private String productDate;
    @Schema(description = "제품 재고", example = "100")
    private Integer productStock;
    @Schema(description = "제품 수정일자", example = "2024-01-01")
    private String productEditDate;
    @Schema(description = "제품 상세정보", example = "해당 제품은 주문과 동시에 주문제작..")
    private String productDetail;
    @Schema(description = "반려동물의 종류", example = "강아지", required = true)
    private String productAnimal;
    @Schema(description = "상품 카테고리", example = "헬스케어")
    private String productCategory;
    @Schema(description = "상품을 필터링할 컬러", example = "Gold")
    private String productColor;
    @Schema(description = "제품 사이즈", example = "S")
    private String productSize;
}
