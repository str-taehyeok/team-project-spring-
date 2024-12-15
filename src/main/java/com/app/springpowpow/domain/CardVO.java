package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
//@Schema(description = "회원 정보")
public class CardVO {
    //    @Schema(description = "회원 번호", example = "21", required = true)
//    private Long id;
    @Schema(description = "카드 번호", example = "1", required = true)
    private Long id;
    @Schema(description = "회원 번호", example = "1", required = true)
    private Long memberId;
    @Schema(description = "카드 회사 이름", example = "신한 카드", required = true)
    private String cardCompany;
    @Schema(description = "카드 고유 번호", example = "1111-1111-1111-1111", required = true)
    private String cardNumber;
    @Schema(description = "카드 보안 코드", example = "888", required = true)
    private int cardSecurityCode;
    @Schema(description = "카드 만료일", example = "MM/YY", required = true)
    private String cardExpirationDate;

}
