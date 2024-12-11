package com.app.springpowpow.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
//@Schema(description = "회원 정보")
public class CommLikesDTO implements Serializable {

    private long id;
    private long memberId;
    private long postId;
    private int commLikesCount;

}
