package com.app.springpowpow.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
//@Schema(description = "회원 정보")
public class FollowsVO implements Serializable {

    private Long id;
    private Long followerMemberId;
    private Long followingMemberId;
    private String followsFollowState;

}
