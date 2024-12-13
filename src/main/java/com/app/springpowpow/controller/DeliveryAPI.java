package com.app.springpowpow.controller;

import com.app.springpowpow.domain.DeliveryVO;
import com.app.springpowpow.service.DeliveryService;
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


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/delivery/*")
public class DeliveryAPI {

    private final DeliveryService deliveryService;

    @Operation(summary = "배송 조건 등록", description = "배송 조건 등록 API")
    @ApiResponse(responseCode = "200", description = "배송 조건 등록 완료")
    @Parameters({
            @Parameter(name = "deliveryFee", description = "배송 가격", schema = @Schema(type = "number"), in = ParameterIn.HEADER),
            @Parameter(name = "deliveryFeeFree", description = "얼마이상 무료배송", schema = @Schema(type = "number"), in = ParameterIn.HEADER),
            @Parameter(name = "deliveryHow", description = "배송 방법", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "deliveryPayWhen", description = "착불여부", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "deliveryCompany", description = "택배사", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
    })
    @PostMapping("write")
    public void insert(@RequestBody DeliveryVO deliveryVO) {
        deliveryService.insertDeliveryInfo(deliveryVO);
    }

}
