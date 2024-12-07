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
