package com.app.springpowpow.controller;

import com.app.springpowpow.service.TossPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/payment/*")
@RequiredArgsConstructor
public class PaymentApi {

    private final TossPaymentService tossPaymentService;

    @PostMapping("/toss")
    public ResponseEntity<String> processPayment(@RequestBody Map<String, Object> paymentData) {
        log.info("Received payment data: {}", paymentData);

        // TossPayments API 호출하여 결제 처리
        String paymentResponse = tossPaymentService.processPayment(paymentData);

        return ResponseEntity.ok(paymentResponse);
    }
}
