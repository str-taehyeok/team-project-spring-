package com.app.springpowpow.service;

import com.app.springpowpow.util.SmsUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    private final JavaMailSender emailSender;

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

    //////////////////////////////////////////////////////////////////////////////////// 이메일인증 로직

    // 인증 코드 저장용 Map 선언
    private Map<String, String> authCodeMap = new HashMap<>();

    @Override
    public ResponseEntity<Map<String, Object>> sendEmailVerification(String memberEmail) {
        Map<String, Object> response = new HashMap<>();

        // 6자리 인증 코드 생성
        String verificationCode = String.format("%06d", (int)(Math.random() * 900000) + 100000);

        synchronized (this) {
            authCodeMap.put(memberEmail, verificationCode);
                log.info("인증코드 이메일코드 확인하기 {}: {}", memberEmail, verificationCode);
        }

        String emailSubject = "POWPOW 이메일 인증 코드";
        String emailContent = "안녕하세요, 인증 코드: " + verificationCode + "를 입력해주세요.";

        // 이메일 전송
        sendEmail(memberEmail, emailSubject, emailContent);

        try {
            // 동기화된 코드 저장
            response.put("message", "인증 코드가 전송되었습니다.");
            log.info("인증 코드가 전송되었습니다. {}", memberEmail);
        } catch (Exception e) {
            response.put("message", "인증 코드 전송에 실패했습니다.");
            log.error("인증 코드 전송에 실패했습니다. {}: {}", memberEmail, e.getMessage());
        }
        // 인증코드 유효기간 5분 설정
        return ResponseEntity.ok(response);
    }

    // 인증 코드 확인
    @Override
    public boolean verifyAuthCode(String memberEmail, String authCode) {
        // 저장된 인증 코드 확인
        String storedAuthCode = authCodeMap.get(memberEmail);

        // 인증 코드가 제대로 저장되었는지 로그로 확인
        log.info("저장된 인증 코드: {}에 대한 인증 코드: {}", memberEmail, storedAuthCode);
        log.info("제공된 인증 코드: {}", authCode);

        // 저장된 인증 코드와 제공된 인증 코드가 동일한지 비교
        return storedAuthCode != null && storedAuthCode.equals(authCode);
    }

    // 이메일 전송 기능
    private void sendEmail(String toEmail, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);      // 받는 사람 이메일 주소
        message.setSubject(subject); // 이메일 제목
        message.setText(content);    // 이메일 내용
        message.setFrom("jaleeck1113@gmail.com"); // 보내는 사람 이메일 주소
        emailSender.send(message); // 이메일 전송
    }
}
