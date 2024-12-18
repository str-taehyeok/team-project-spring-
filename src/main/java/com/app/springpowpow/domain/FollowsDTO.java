package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class FollowsDTO {

    @Schema(description = "ID", example = "1", required = true)
    private Long id;

    @Schema(description = "팔로워 회원 ID", example = "100", required = true)
    private Long followerMemberId;

    @Schema(description = "팔로잉 회원 ID", example = "200", required = true)
    private Long followingMemberId;

    @Schema(description = "팔로우 상태", example = "1", required = true)
    private String followsFollowState;

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

    @Schema(description = "회원 가입날짜", example = "2024-12-09")
    private String memberDate;

    @Schema(description = "사업자번호", example = "000-00-00000")
    private String memberBusinessNumber;

    @Schema(description = "회원 권한", example = "1")
    private int memberAuth;

    @Schema(description = "회원 프로바이더", example = "구매자")
    private String memberProvider;

    @Schema(description = "판매자 업체명", example = "포포")
    private String memberBusinessName;

    @Schema(description = "은행명", example = "기업")
    private String memberBank;

    @Schema(description = "계좌번호", example = "471010000000")
    private String memberBankAccount;

}
