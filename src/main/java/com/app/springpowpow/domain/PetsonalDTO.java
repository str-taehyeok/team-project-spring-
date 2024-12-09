package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@Schema(description = "펫스널 DTO 정보")
public class PetsonalDTO implements Serializable {
    //    @Schema(description = "회원 번호", example = "21", required = true)
    //    private Long id;
    private Long id;
    private Long petId;
    private int petsonalChic;
    private int petsonalCute;
    private int petsonalCalm;
    private int petsonalActive;
    private int petsonalLazy;
    private int petsonalDiligent;
    private int petsonalCoward;
    private int petsonalBrave;
    private String petName;
    private char petKind;
    private String petImage;
    private String petGender;
    private String petBreed;
    private String petBirth;
    private String petVet;
    private char petNeuter;
    private String petColor;

}
