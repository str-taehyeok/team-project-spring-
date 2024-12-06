package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
//@Schema(description = "회원 정보")
public class PetDTO implements Serializable {
//    @Schema(description = "회원 번호", example = "21", required = true)

    private Long id;
    private String petKind;
    private String petImage;
    private String petName;
    private String petGender;
    private String petBreed;
    private String petBirth;
    private String petVet;
    private double petWeight;
    private String petNeuter;
    private String petColor;
    private String MemberEmail;
    private String MemberName;
}
