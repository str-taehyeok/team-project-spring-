package com.app.springpowpow.controller;

import com.app.springpowpow.domain.MemberVO;
import com.app.springpowpow.domain.OrderCancelVO;
import com.app.springpowpow.domain.OrderDTO;
import com.app.springpowpow.domain.OrderVO;
import com.app.springpowpow.repository.OrderCancelDAO;
import com.app.springpowpow.service.OrderCancelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/orderCancels/*")
public class OrderCancelAPI {

    private final OrderCancelService orderCancelService;
    private final OrderCancelDAO orderCancelDAO;

    @Operation(summary = "주문 취소 등록", description = "주문 취소 API")
    @ApiResponse(responseCode = "200", description = "주문 취소 완료")
    @PostMapping("write")
    public OrderCancelVO write(@RequestBody OrderCancelVO orderCancelVO) {
        orderCancelService.insertOrderCancel(orderCancelVO);
        return new OrderCancelVO();
    }

    @Operation(summary = "전체 주문 취소 조회", description = "전체 주문 취소 API")
    @ApiResponse(responseCode = "200", description = "전체 주문 취소 조회 완료")
    @GetMapping("list")
    public List<OrderCancelVO> list() {
        return orderCancelService.findAll();
    }

//    @Operation(summary = "단일 주문 취소 조회", description = "단일 주문 취소 조회 API")
//    @ApiResponse(responseCode = "200", description = "단일 주문 취소 조회 완료")
//    @GetMapping("orderCancel/{id}")
//    public OrderCancelVO orderCancel(@PathVariable Long id){
//        Optional<OrderCancelVO> foundUser = orderCancelService.
//        if (foundUser.isPresent()) {
//        return foundUser.get();
//    }
//
//        return new OrderCancelVO();
//    }


}