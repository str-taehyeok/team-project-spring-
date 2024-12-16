package com.app.springpowpow.controller;

import com.app.springpowpow.domain.CouponVO;
import com.app.springpowpow.service.CouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/coupons/*")
public class CouponAPI {

    private final CouponService couponService;

//    쿠폰 생성

    @Operation(summary = "쿠폰 생성", description = "쿠폰을 생성할 수 있는 API")
//    @Parameters({
//            @Parameter(name = "id", description = "쿠폰 번호", schema = @Schema(type = "number"), in = ParameterIn.HEADER, required = true),
//            @Parameter(name = "couponTitle", description = "쿠폰 이름", schema = @Schema(type= "string"), in = ParameterIn.HEADER, required = true),
//            @Parameter(name = "couponCategory", description = "쿠폰 카테고리", schema = @Schema(type= "string"), in = ParameterIn.HEADER, required = true),
//            @Parameter(name = "couponAnimal", description = "쿠폰 동물 카테고리", schema = @Schema(type= "string"), in = ParameterIn.HEADER, required = true),
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

    @Operation(summary = "쿠폰 수정", description = "쿠폰 수정할 수 있는 API")
    @Parameter( name = "id", description = "쿠폰 번호", schema = @Schema(type="number"), in = ParameterIn.PATH, required = true )
    @ApiResponse(responseCode = "200", description = "쿠폰 수정 완료")
    @PutMapping("coupons/{id}")
    public CouponVO modify(@PathVariable Long id, @RequestBody CouponVO couponVO){
        couponVO.setId(id);
        couponService.modifyCoupon(couponVO);
        return couponVO;
    }


    @Operation(summary = "전체 쿠폰 조회", description = "전체 쿠폰 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "쿠폰 조회 완료")
    @GetMapping("list")
    public List<CouponVO> list(){
        return couponService.getAllCoupons();
    }

    //  쿠폰 단일 조회
    @Operation(summary = "쿠폰 정보 조회", description = "쿠폰 정보를 전체 조회할 수 있는 API")
    @Parameter( name = "id", description = "쿠폰 번호", schema = @Schema(type="number"), in = ParameterIn.HEADER, required = true )
    @GetMapping("coupons/{id}")
    public CouponVO getCoupon(@PathVariable Long id){
        Optional<CouponVO> foundCoupon = couponService.getCouponById(id);
        if(foundCoupon.isPresent()){
            return foundCoupon.get();
        }
        return new CouponVO();
    }

    //  쿠폰리스트 조회 (멤버)
    @Operation(summary = "쿠폰 정보 조회(멤버)", description = "쿠폰 정보를 전체 조회할 수 있는 API")
    @Parameter( name = "id", description = "멤버 번호", schema = @Schema(type="number"), in = ParameterIn.HEADER, required = true )
    @GetMapping("/coupons/member/{id}")
    public List<CouponVO> getCouponsByMemberId(@PathVariable Long id){
        return couponService.getCouponByMemberId(id);
    }



    //    쿠폰 삭제
    @Operation(summary = "쿠폰 삭제", description = "쿠폰 삭제할 수 있는 API")
    @Parameter( name = "id", description = "쿠폰 번호", schema = @Schema(type="number"), in = ParameterIn.PATH, required = true )
    @ApiResponse(responseCode = "200", description = "쿠폰 삭제 완료")
    @DeleteMapping("coupons/{id}")
    public void delete(@PathVariable Long id){
        couponService.deleteCoupon(id);
    }

}
