package com.app.springpowpow.controller;

import com.app.springpowpow.domain.CartOrderDTO;
import com.app.springpowpow.service.CartOrderService;
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
@RequestMapping("/carts/*")
public class CartOrderAPI {

    private final CartOrderService cartOrderService;

//   카트에 담긴 제품 전체 조회
    @Operation(summary = "장바구니에 담긴 제품 전체 조회", description = "제품 전체 조회 API")
    @Parameter(name = "Id", description = "ID", schema = @Schema(type = "number"), in = ParameterIn.QUERY)
    @PostMapping("all-cart/{id}")
    public List<CartOrderDTO> getCartOrder(@RequestParam Long id) {
        return cartOrderService.getCartOrder(id);
    }

//    카트에 담긴 제품 단일 조회
    @Operation(summary = "장바구니 제품 단일 조회", description = "제품 단일 조회 API")
    @Parameter(name = "Id", description = "ID", schema = @Schema(type = "number"), in = ParameterIn.QUERY)
    @GetMapping("cart-orders/{id}")
    public Optional<CartOrderDTO> getCartOrderById(@RequestParam Long id) {
        return cartOrderService.getCartOrderById(id);
    }

//    카트에 담긴 제품의 수량, 쿠폰 변경
    @Operation(summary = "장바구니 제품 수량, 쿠폰 수정", description = "장바구니 수정할 수 있는 API")
    @Parameter(name = "id", description = "ID", schema = @Schema(type = "number"), in = ParameterIn.PATH, required = true )
    @ApiResponse(responseCode = "200", description = "상품 수정 완료")
    @PutMapping("cart/{id}")
    public CartOrderDTO modify(@PathVariable Long id, @RequestBody CartOrderDTO cartOrderDTO) {
        cartOrderDTO.setId(id);
        cartOrderService.modify(cartOrderDTO);
        Optional<CartOrderDTO> foundCart = cartOrderService.getCartOrderById(cartOrderDTO.getId());
        if(foundCart.isPresent()){
            return foundCart.get();
        }
        return new CartOrderDTO();
    }

//    카트에 담긴 제품 삭제
    @Operation(summary = "카트 제품 삭제", description = "카트 제품 삭제할 수 있는 API")
    @Parameter(name = "id", description = "ID", schema = @Schema(type = "number"), in = ParameterIn.PATH, required = true )
    @ApiResponse(responseCode = "200", description = "카트 제품 삭제 완료")
    @DeleteMapping("cart/{id}")
    public void delete(@PathVariable Long id) {
        cartOrderService.remove(id);
    }


}
