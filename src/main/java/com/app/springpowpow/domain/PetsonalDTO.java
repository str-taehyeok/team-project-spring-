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
    @Schema(description = "펫스널 번호", example = "21", required = true)
    private Long id;
    @Schema(description = "펫 번호", example = "21", required = true)
    private Long petId;
    @Schema(description = "시크 점수", example = "1")
    private int petsonalChic;
    @Schema(description = "귀여움 점수", example = "1")
    private int petsonalCute;
    @Schema(description = "차분함 점수", example = "1")
    private int petsonalCalm;
    @Schema(description = "활동력 점수", example = "1")
    private int petsonalActive;
    @Schema(description = "게으름 점수", example = "1")
    private int petsonalLazy;
    @Schema(description = "부지런함 점수", example = "1")
    private int petsonalDiligent;
    @Schema(description = "겁 점수", example = "1")
    private int petsonalCoward;
    @Schema(description = "용감함 점수", example = "1")
    private int petsonalBrave;
    @Schema(description = "반려종류", example = "반려견")
    private String petKind;
    @Schema(description = "이미지", example = "사진")
    private String petImage;
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

}
