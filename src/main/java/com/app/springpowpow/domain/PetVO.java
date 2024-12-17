package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@Schema(description = "반려 정보")
public class PetVO implements Serializable {
    @Schema(description = "마이펫 번호", example = "1", required = true)
    private Long id;
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
    @Schema(description = "회원 번호", example = "23")
    private Long memberId;



}
