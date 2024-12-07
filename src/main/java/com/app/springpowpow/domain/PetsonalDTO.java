package com.app.springpowpow.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
//@Schema(description = "회원 정보")
public class PetsonalDTO implements Serializable {
    //    @Schema(description = "회원 번호", example = "21", required = true)
    //    private Long id;
    private Long id;
    private Long petId;
    private Long memberId;
    private int petsonalChic;
    private int petsonalCute;
    private int petsonalCalm;
    private int petsonalActive;
    private int petsonalLazy;
    private int petsonalDiligent;
    private int petsonalCoward;
    private int petsonalBrave;

}
