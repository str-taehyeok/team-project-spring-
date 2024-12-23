package com.app.springpowpow.controller;

import com.app.springpowpow.domain.DeliveryDTO;
import com.app.springpowpow.domain.DeliveryVO;
import com.app.springpowpow.domain.ProductDTO;
import com.app.springpowpow.service.DeliveryService;
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
@RequestMapping("/deliveries/*")
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


    @GetMapping("orders")
    public List<DeliveryDTO> getOrders() {
        return deliveryService.selectAllDeliveryInfo();
    }



    @Operation(summary = "배송 조건 수정", description = "배송 조건 수정 API")
    @ApiResponse(responseCode = "200", description = "배송 조건 수정 완료")
    @Parameters({
            @Parameter(name = "deliveryFee", description = "배송 가격", schema = @Schema(type = "number"), in = ParameterIn.HEADER),
            @Parameter(name = "deliveryFeeFree", description = "얼마이상 무료배송", schema = @Schema(type = "number"), in = ParameterIn.HEADER),
            @Parameter(name = "deliveryHow", description = "배송 방법", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "deliveryPayWhen", description = "착불여부", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "deliveryCompany", description = "택배사", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
    })
    @PutMapping("seller-product/{id}")
    public DeliveryDTO modify(@PathVariable Long id, @RequestBody DeliveryDTO deliveryDTO) {
        deliveryDTO.setId(id);
        deliveryService.updateDeliveryInfo(deliveryDTO);
        return deliveryDTO;
    }


    @Operation(summary = "배송 조건 삭제", description = "배송조건을 삭제하는 API")
    @Parameter(name = "id", description = "배송조건 삭제", schema = @Schema(type="number"))//DB의 스키마가 아니라, swagger에서 인식하기 위한 타입in = ParameterIn.PATH, //path 로 전달required = true //반드시 전달)
    @ApiResponse(responseCode = "200", description = "배송 조건 삭제 완료")
    @DeleteMapping("product/{id}")
    public void delete(@PathVariable Long id){
        deliveryService.deleteDeliveryInfo(id);
    }


    
}
