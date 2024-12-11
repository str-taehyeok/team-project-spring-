package com.app.springpowpow.controller;

import com.app.springpowpow.domain.ProductVO;
import com.app.springpowpow.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product/*")
public class ProductAPI {
    private final ProductService productService;


//    제품 등록
    @Operation(summary = "제품 등록", description = "제품등록 API")
    @ApiResponse(responseCode = "200", description = "제품등록 완료")
    @PostMapping("write")
    public void insert(@RequestBody ProductVO productVO) {
        productService.insertNewProduct(productVO);
    }


    @Operation(summary = "제품 전체 조회", description = "모든 제품을 리스트로 볼수 있는 API")
    @Parameters({
            @Parameter(name = "id", description = "제품 번호", schema = @Schema(type = "number"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberId", description = "회원 id", schema = @Schema(type = "number"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "productName", description = "제품명", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "productPrice", description = "제품가격", schema = @Schema(type = "number"), in = ParameterIn.HEADER),
            @Parameter(name = "productCode", description = "제품 코드", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "productDate", description = "제품 등록일자", schema = @Schema(type = "string", format = "date"), in = ParameterIn.HEADER),
            @Parameter(name = "productStock", description = "제품 재고", schema = @Schema(type = "number"), in = ParameterIn.HEADER),
            @Parameter(name = "productEditDate", description = "제품 수정일자", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "productDetail", description = "제품 상세정보", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "productImage1", description = "제품 이미지1", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "productImage2", description = "제품 이미지2", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "productImage3", description = "제품 이미지3", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "productImage4", description = "제품 이미지4", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "productAnimal", description = "반려동물 종류", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "productCategory", description = "제품 카테고리", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "productColor", description = "제품 펫스널 컬러", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "productSize", description = "제품 사이즈", schema = @Schema(type = "char"), in = ParameterIn.HEADER)
    })
    @GetMapping("/store-dog")
    public List<ProductVO> products() {
        return productService.selectAllProducts();
    }







}
