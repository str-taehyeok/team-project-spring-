package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "쿠폰과 상품 정보")
public class CouponDTO {

    @Schema(description = "쿠폰 번호", example = "21", required = true)
    private Long id;
    @Schema(description = "멤버 번호", example = "11")
    private Long memberId;
    @Schema(description = "쿠폰 이름", example = "쿠폰 이름", required = true)
    private String couponTitle;
    @Schema(description = "쿠폰 카테고리", example = "장난감", required = true)
    private String couponCategory;
    @Schema(description = "쿠폰 카테고리 동물", example = "고양이", required = true)
    private String couponCategoryAnimal;
    @Schema(description = "쿠폰 코드", example = "ABCDEFGH1234125", required = true)
    private String couponCode;
    @Schema(description = "쿠폰 시작 시간", example = "2024-11-21")
    private String couponStart;
    @Schema(description = "쿠폰 종료 시간", example = "2024-12-21")
    private String couponEnd;
    @Schema(description = "쿠폰 내용", example = "이것은 쿠폰내용", required = true)
    private String couponContent;
    @Schema(description = "쿠폰 수량", example = "44")
    private Integer couponQuantity;
    @Schema(description = "쿠폰 할인율", example = "20")
    private Integer couponDiscountRate;

    //    @Schema(description = "제품 번호", example = "1", required = true)
    private Long productId;
    //    @Schema(description = "제품명", example = "덴탈 치약")
    private String productName;
    //    @Schema(description = "제품가격", example = "25000")
    private int productPrice;
    //    @Schema(description = "제품가격", example = "25000")
    private int productRealPrice;
    //    @Schema(description = "제품 코드", example = "000000")
    private String productCode;
    //    @Schema(description = "제품 등록일자", example = "2024-01-01")
    private String productDate;
    //    @Schema(description = "제품 재고", example = "100")
    private int productStock;
    //    @Schema(description = "제품 수정일자", example = "2024-01-01")
    private String productEditDate;
    //    @Schema(description = "제품 상세정보", example = "해당 제품은 주문과 동시에 주문제작..")
    private String productDetail;
    //    @Schema(description = "제품사진 1", example = "product.png")
    private String productImage1;
    //    @Schema(description = "제품사진 2", example = "product.png")
    private String productImage2;
    //    @Schema(description = "제품사진 3", example = "product.png")
    private String productImage3;
    //    @Schema(description = "제품사진 4", example = "product.png")
    private String productImage4;
    //    @Schema(description = "반려동물의 종류", example = "강아지", required = true)
    private String productAnimal;
    //    @Schema(description = "상품 카테고리", example = "헬스케어")
    private String productCategory;
    //    @Schema(description = "상품을 필터링할 컬러", example = "Gold")
    private String productColor;
    //    @Schema(description = "제품 사이즈", example = "S")
    private char productSize;


}
