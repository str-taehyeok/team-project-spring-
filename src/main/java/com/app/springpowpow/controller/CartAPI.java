package com.app.springpowpow.controller;

import com.app.springpowpow.domain.CartDTO;
import com.app.springpowpow.domain.CartVO;
import com.app.springpowpow.domain.CommLikesDTO;
import com.app.springpowpow.service.CartService;
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
@RequestMapping("/cart/*")
public class CartAPI {

    private final CartService cartService;

//    제품 전체 조회
    @Operation(summary = "제품 전체 조회", description = "제품 전체 조회 API")
    @Parameter(name = "memberId", description = "회원 ID", schema = @Schema(type = "number"), in = ParameterIn.QUERY)
    @PostMapping("all-carts")
    public List<CartDTO> getCartById(@RequestBody Long memberId) {
        return cartService.getCartById(memberId);
    }

//   제품 단일 조회
    @Operation(summary = "제품 단일 조회", description = "제품 단일 조회 API")
    @Parameter(name = "memberId", description = "회원 ID", schema = @Schema(type = "number"), in = ParameterIn.QUERY)
    @GetMapping("carts")
    public Optional<CartDTO> getCartProductById(@RequestParam Long memberId) {
        return cartService.getCartProductById(memberId);
    }

//    제품 추가
    @Operation(summary = "제품 장바구니에 추가", description = "장바구니 추가 API")
    @ApiResponse(responseCode = "200", description = "장바구니 추가 완료")
    @PostMapping("checks")
    public void addCart(@RequestBody CartVO cartVO) {
        log.info("cart {}", cartVO.toString());
        cartService.addCart(cartVO);
    }

//    제품 취소
    @Operation(summary = "제품 장바구니 취소", description = "제픔 취소 API")
    @ApiResponse(responseCode = "200", description = "제품 취소 완료")
    @DeleteMapping("cancel-checks")
    public void removeCart(@RequestBody CartVO cartVO) {
        log.info("cartCancel {}", cartVO.toString());
        cartService.removeCart(cartVO);
    }

//   회원 드롭
    @Operation(summary = "회원 탈퇴시 테이블에서 삭제", description = "회원 삭제 API")
    @ApiResponse(responseCode = "200", description = "회원 삭제 완료")
    @DeleteMapping("delete-members")
    public void removeMember(@PathVariable Long memberId) {
        cartService.removeMember(memberId);
    }

}
