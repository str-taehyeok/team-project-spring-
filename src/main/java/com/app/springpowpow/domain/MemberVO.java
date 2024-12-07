package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
//@Schema(description = "회원 정보")
public class MemberVO implements Serializable {
//    @Schema(description = "회원 번호", example = "21", required = true)
//    private Long id;
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPHone;
    private String memberNickname;
    private String memberZipcode;
    private String memberAddress;
    private String memberAddressDetail;
    private String memberImage;
    private char memberSmsCheck;
    private char memberEmailCheck;
    private String memberDate;
    private String memberBusinessNumber;
    private int memberAuth;
    private String memberProvider;
}
