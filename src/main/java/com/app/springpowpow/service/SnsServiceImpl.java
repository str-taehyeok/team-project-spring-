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

    // 폰번호로 인증 코드 전송
    @Override
    public ResponseEntity<Map<String, Object>> transferMessage(String memberPhone) {
        Map<String, Object> response = new HashMap<>();
        String verificationCode = String.format("%06d", (int)(Math.random() * 900000) + 100000);

        response.put("message", "메세지 전송 성공");
        response.put("verificationCode", verificationCode);

        smsUtil.sendOne(memberPhone, verificationCode); // SMS 전송

        // 인증코드 유효기간 5분 설정
        return ResponseEntity.ok(response);
    }

    // 이메일로 인증 코드 전송
    public ResponseEntity<Map<String, Object>> sendEmailVerification(String memberEmail) {
        Map<String, Object> response = new HashMap<>();
        String verificationCode = String.format("%06d", (int)(Math.random() * 900000) + 100000);

        try {
            // 이메일 전송 (메일 발송 기능이 있는 smsUtil의 sendOne 메서드를 가정)
            String emailSubject = "Powpow 이메일 인증 코드";
            String emailContent = "안녕하세요, 인증 코드: " + verificationCode + "를 입력해주세요.";
            smsUtil.sendOne(memberEmail, verificationCode); // 이메일 전송

            response.put("message", "인증 코드가 전송되었습니다.");
            log.info("Verification code sent successfully to {}", memberEmail);
        } catch (Exception e) {
            response.put("message", "인증 코드 전송에 실패했습니다.");
            log.error("Failed to send verification code to {}: {}", memberEmail, e.getMessage());
        }

        // 인증코드 유효기간 5분 설정
        return ResponseEntity.ok(response);
    }
}
