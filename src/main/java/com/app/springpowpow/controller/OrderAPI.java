package com.app.springpowpow.controller;

import com.app.springpowpow.domain.OrderDTO;
import com.app.springpowpow.domain.ReviewDTO;
import com.app.springpowpow.service.OrderService;
import com.app.springpowpow.service.ReviewService;
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
@RequestMapping("/order/*")
public class OrderAPI {
    private final OrderService orderService;

    @Operation(summary = "주문 등록", description = "주문 API")
    @ApiResponse(responseCode = "200", description = "주문 완료")
    @Parameters({
            @Parameter(name = "productId", description = "제품 id", schema = @Schema(type = "number"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberId", description = "회원 id", schema = @Schema(type = "number"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "deliveryId", description = "배송종류 id", schema = @Schema(type = "number"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "orderNumber", description = "주문번호", schema = @Schema(type = "String"), in = ParameterIn.HEADER),
            @Parameter(name = "orderAddress", description = "주소", schema = @Schema(type = "String"), in = ParameterIn.HEADER),
            @Parameter(name = "productCount", description = "제품 개수", schema = @Schema(type = "String"), in = ParameterIn.HEADER),
    })
    @PostMapping("write")
    public void insert(@RequestBody OrderDTO orderDTO) {
        orderService.insertOrder(orderDTO);
    }

    @Operation(summary = "주문 전체 조회", description = "모든 주문을 리스트로 볼수 있는 API")
    @GetMapping("order")
    public List<OrderDTO> orders() {
        return orderService.getOrders();
    }



    //    배송 삭제
    @Operation(summary = "주문 삭제", description = "주문 삭제하는 API")
    @Parameter(name = "id", description = "주문 삭제", schema = @Schema(type="number"))//DB의 스키마가 아니라, swagger에서 인식하기 위한 타입in = ParameterIn.PATH, //path 로 전달required = true //반드시 전달)
    @ApiResponse(responseCode = "200", description = "주문 삭제 완료")
    @DeleteMapping("order/{id}")
    public void delete(@PathVariable Long id){
        orderService.deleteOrderById(id);
    }




}
