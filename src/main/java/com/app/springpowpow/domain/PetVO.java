package com.app.springpowpow.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
//@Schema(description = "회원 정보")
public class PetVO implements Serializable {
//    @Schema(description = "회원 번호", example = "21", required = true)
    private Long id;
    private String kind;
    private String image;
    private String name;
    private String gender;
    private String breed;
    private String birth;
    private String vet;
    private String weight;
    private String neuter;
    private String color;



}
