package com.app.springpowpow.service;

import com.app.springpowpow.util.SmsUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class SnsServiceImpl implements SnsService {

    private final SmsUtil smsUtil;

    @Override
    public ResponseEntity<Map<String, Object>> transferMessage(String memberPhone) {
//        폰넘버
        Map<String, Object> response = new HashMap<>();
        String verificationCode = String.format("%06d", (int)(Math.random() * 900000) + 100000);

        response.put("message", "메세지 전송 성공");
        response.put("verificationCode", verificationCode);

        smsUtil.sendOne(memberPhone, verificationCode);

        //인증코드 유효기간 5분 설정
        return ResponseEntity.ok(response);

    }





}
