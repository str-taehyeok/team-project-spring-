package com.app.springpowpow.controller;

import com.app.springpowpow.domain.CouponVO;
import com.app.springpowpow.service.CouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/coupon/*")
public class CouponAPI {

    private final CouponService couponService;

//    쿠폰 생성

    @Operation(summary = "쿠폰 생성", description = "쿠폰을 생성할 수 있는 API")
//    @Parameters({
//            @Parameter(name = "id", description = "쿠폰 번호", schema = @Schema(type = "number"), in = ParameterIn.HEADER, required = true),
//            @Parameter(name = "memberId", description = "멤버 번호", schema = @Schema(type= "number"), in = ParameterIn.HEADER),
//            @Parameter(name = "couponTitle", description = "쿠폰 이름", schema = @Schema(type= "string"), in = ParameterIn.HEADER, required = true),
//            @Parameter(name = "couponCategory", description = "쿠폰 카테고리", schema = @Schema(type= "string"), in = ParameterIn.HEADER, required = true),
//            @Parameter(name = "couponCategoryAnimal", description = "쿠폰 동물 카테고리", schema = @Schema(type= "string"), in = ParameterIn.HEADER, required = true),
//            @Parameter(name = "couponCode", description = "쿠폰 코드", schema = @Schema(type= "string"), in = ParameterIn.HEADER, required = true),
//            @Parameter(name = "couponStart", description = "쿠폰 시작 기간", schema = @Schema(type= "string"), in = ParameterIn.HEADER),
//            @Parameter(name = "couponEnd", description = "쿠폰 종료 기간", schema = @Schema(type= "string"), in = ParameterIn.HEADER),
//            @Parameter(name = "couponContent", description = "쿠폰 내용", schema = @Schema(type= "string"), in = ParameterIn.HEADER, required = true),
//            @Parameter(name = "couponQuantity", description = "쿠폰 수량", schema = @Schema(type= "number"), in = ParameterIn.HEADER),
//            @Parameter(name = "couponDiscountRate", description = "쿠폰 할인 비율", schema = @Schema(type= "number"), in = ParameterIn.HEADER)
//    })
    @ApiResponse(responseCode = "200", description = "쿠폰 생성 완료")
    @PostMapping("write")
    public CouponVO write(@RequestBody CouponVO couponVO){
        couponService.registerCoupon(couponVO);
        return couponVO;
    }
}
