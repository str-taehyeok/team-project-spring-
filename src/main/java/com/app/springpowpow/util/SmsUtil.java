package com.app.springpowpow.util;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
@Slf4j
public class SmsUtil {

    @Value("${coolsms.api.key}")
    private String apiKey;
    @Value("${coolsms.api.secret}")
    private String apiSecretKey;

    private DefaultMessageService messageService;

    private final JavaMailSender javaMailSender;

    // 생성자 주입
    public SmsUtil(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @PostConstruct
    private void init() {
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecretKey, "https://api.coolsms.co.kr");
    }

    // 단일 메시지 발송 예제 (휴대폰 인증 코드 발송 부분)
    public SingleMessageSentResponse sendOne(String to, String verificationCode) {
        Message message = new Message();
        String toPhoneNumber = to.replace("\"", "");
        log.info("toPhoneNumber {}", toPhoneNumber);

        message.setFrom("01030204067"); // 보내는 사람
        message.setTo(toPhoneNumber); // 받는 사람
        message.setText("[Powpow] 인증번호 [" + verificationCode + "]를 입력해주세요.");

        return this.messageService.sendOne(new SingleMessageSendingRequest(message));
    }

    // 이메일 발송 메서드 추가
    public void sendEmail(String to, String subject, String content) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content);

        javaMailSender.send(mimeMessage);
        log.info("이메일 전송 완료: {}", to);
    }
}
