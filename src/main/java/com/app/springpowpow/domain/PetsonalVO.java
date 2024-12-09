package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@Schema(description = "펫스널 정보")
public class PetsonalVO implements Serializable {
    @Schema(description = "펫스널 번호", example = "21", required = true)
    private Long id;
    @Schema(description = "펫 번호", example = "21", required = true)
    private Long petId;
    @Schema(description = "회원 번호", example = "반려견", required = true)
    private Long memberId;
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
}
