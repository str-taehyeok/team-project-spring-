package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@Schema(description = "펫 정보")
public class PetDTO implements Serializable {

    @Schema(description = "마이펫 번호", example = "1", required = true)
    private Long id;
    @Schema(description = "회원 번호", example = "23")
    private Long memberId;
    @Schema(description = "반려종류", example = "반려견")
    private String petKind;
    @Schema(description = "반려 이미지 경로", example = "powpow.jpg")
    private String petFilePath;
    @Schema(description = "반려 이미지 이름", example = "powpow")
    private String petFileName;
    @Schema(description = "반려 이름", example = "레오")
    private String petName;
    @Schema(description = "반려 성별", example = "수컷", required = true)
    private String petGender;
    @Schema(description = "반려 품종", example = "코카스파니엘", required = true)
    private String petBreed;
    @Schema(description = "반려 생일", example = "2012/11/07")
    private String petBirth;
    @Schema(description = "동물병원", example = "포포동물병원")
    private String petVet;
    @Schema(description = "반려 몸무게", example = "12kg")
    private double petWeight;
    @Schema(description = "반려 중성화", example = "했어요")
    private String petNeuter;
    @Schema(description = "반려 색상", example = "yellow")
    private String petColor;
    @Schema(description = "회원 이메일", example = "test123@naver.com", required = true)
    private String memberEmail;
    @Schema(description = "회원 비밀번호", example = "test123@@")
    private String memberPassword;
    @Schema(description = "회원 이름", example = "홍길동", required = true)
    private String memberName;
    @Schema(description = "회원 전화번호", example = "010-0000-0000", required = true)
    private String memberPhone;
    @Schema(description = "회원 닉네임", example = "징징이")
    private String memberNickname;
    @Schema(description = "우편번호", example = "12345")
    private String memberZipcode;
    @Schema(description = "회원 주소", example = "서울시 강남구 역삼동")
    private String memberAddress;
    @Schema(description = "회원 상세주소", example = "141-15 센터필드")
    private String memberAddressDetail;
    @Schema(description = "회원 이미지파일 이름", example = "default-user-image")
    private String memberFileName;
    @Schema(description = "회원 이미지파일 경로", example = "default-user-image.jpg")
    private String memberFilePath;
    @Schema(description = "회원 문자수신동의", example = "1")
    private char memberSmsCheck;
    @Schema(description = "회원 이메일수신동의", example = "1")
    private char memberEmailCheck;
}
