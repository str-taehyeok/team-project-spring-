package com.app.springpowpow.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Map;

@Slf4j
@RestController
@Service
public class TossPaymentServiceImpl implements TossPaymentService {

    @Value("${toss.payments.api.key}")
    private String apiKey;

    @Value("${toss.payments.api.url}")
    private String apiUrl;

    @Override
    public String processPayment(Map<String, Object> paymentData) {

        RestTemplate restTemplate = new RestTemplate();
        // API 키를 Base64로 인코딩
        String encodedApiKey = Base64.getEncoder().encodeToString((apiKey + ":").getBytes());

        // HTTP 헤더 설정 (Authorization: Basic <encodedApiKey>)
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + encodedApiKey);  // 인코딩된 API 키를 Basic 인증 헤더에 추가
        headers.set("Content-Type", "application/json; charset=UTF-8");  // UTF-8 인코딩 명시
        headers.set("Accept", "application/json; charset=UTF-8");  // 응답도 UTF-8로 처리

        // HTTP 바디에 결제 데이터 추가
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(paymentData, headers);

        // POST 요청을 보내고 응답을 받음
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
//            PaymentDTO paymentDTO = new PaymentDTO();
            JsonNode jsonResponse = objectMapper.readTree(response.getBody());

            log.info("jsonResponse : {}", jsonResponse.toString());

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // 응답 상태 코드 확인
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Failed to confirm payment: " + response.getBody());
        }

        return response.getBody();
    }
}
