package com.app.springpowpow.controller;

import com.app.springpowpow.domain.OrderDTO;
import com.app.springpowpow.domain.OrderVO;
import com.app.springpowpow.domain.ProductDTO;
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
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/orders/*")
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
    public OrderVO write(@RequestBody OrderVO orderVO) {
        orderService.insertNewOrder(orderVO);
        return new OrderVO();
    }

    @Operation(summary = "주문 전체 조회", description = "모든 주문을 리스트로 볼수 있는 API")
    @ApiResponse(responseCode = "200", description = "전체 주문 조회 완료")
    @GetMapping("orders")
    public List<OrderDTO> orders() {
        return orderService.selectAllOrders();
    }

    //  주문 단일
//    @Operation(summary = "주문 단일 조회", description = "주문 한가지를 볼수 있는 API")
//    @ApiResponse(responseCode = "200", description = "단일 주문 조회 완료")
//    @GetMapping("order/{id}")
//    public OrderDTO product(@PathVariable Long id){
//        Optional<OrderDTO> foundOrder =  orderService.getOrderById(id);
//        if (foundOrder.isPresent()) {
//        return foundOrder.get();
//        }
//        return new OrderDTO();
//    }

    //    배송 삭제
    @Operation(summary = "주문 삭제", description = "주문 삭제하는 API")
    @Parameter(name = "id", description = "주문 삭제", schema = @Schema(type="number"))//DB의 스키마가 아니라, swagger에서 인식하기 위한 타입in = ParameterIn.PATH, //path 로 전달required = true //반드시 전달)
    @ApiResponse(responseCode = "200", description = "주문 삭제 완료")
    @DeleteMapping("order/{id}")
    public void delete(@PathVariable Long id){
        orderService.deleteOrderById(id);
    }

//    주문 수정
    @Operation(summary = "주문 수정", description = "주문 수정하는 API")
    @Parameter( name = "id", description = "주문 번호", schema = @Schema(type="number"), in = ParameterIn.HEADER, required = true)
    @ApiResponse(responseCode = "200", description = "주문 수정 완료")
    @PutMapping("order/{id}")
    public OrderVO modify(@PathVariable Long id, @RequestBody OrderVO orderVO){
        orderVO.setId(id);
        orderService.updateOrderById(orderVO);
        return orderVO;
    }

    @Operation(summary = "특정 멤버 주문 목록", description = "특정 멤버 주문 조회하는 API")
    @Parameter( name = "id", description = "멤버번호", schema = @Schema(type = "number"), in = ParameterIn.PATH, required = true)
    @ApiResponse(responseCode = "200", description = "주문 조회 완료")
    @GetMapping("order/member/{id}")
    public List<OrderVO> selectByMember(@PathVariable Long id){
        return orderService.selectOrderByMemberId(id);
    }





}
