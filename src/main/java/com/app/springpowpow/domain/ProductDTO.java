package com.app.springpowpow.domain;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "제품 DTO 정보")
public class ProductDTO {
        @Schema(description = "제품 번호", example = "1", required = true)
    private Long id;
        @Schema(description = "회원 번호", example = "21", required = true)
    private Long memberId;
        @Schema(description = "제품명", example = "덴탈 치약")
    private String productName;
        @Schema(description = "제품가격", example = "25000")
    private int productPrice;
        @Schema(description = "제품 코드", example = "000000")
    private String productCode;
        @Schema(description = "제품 등록일자", example = "2024-01-01")
    private String productDate;
        @Schema(description = "제품 재고", example = "100")
    private int productStock;
        @Schema(description = "제품 수정일자", example = "2024-01-01")
    private String productEditDate;
        @Schema(description = "제품 상세정보", example = "해당 제품은 주문과 동시에 주문제작..")
    private String productDetail;
        @Schema(description = "제품사진 1", example = "product.png")
    private String productImage1;
        @Schema(description = "제품사진 2", example = "product.png")
    private String productImage2;
        @Schema(description = "제품사진 3", example = "product.png")
    private String productImage3;
        @Schema(description = "제품사진 4", example = "product.png")
    private String productImage4;
        @Schema(description = "반려동물의 종류", example = "강아지", required = true)
    private String productAnimal;
        @Schema(description = "상품 카테고리", example = "헬스케어")
    private String productCategory;
        @Schema(description = "상품을 필터링할 컬러", example = "Gold")
    private String productColor;
        @Schema(description = "제품 사이즈", example = "S")
    private char productSize;


//    배송
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
