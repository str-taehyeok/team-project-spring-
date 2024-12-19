package com.app.springpowpow.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class FollowsVO {

    private Long id;
    private Long followerMemberId;
    private Long followingMemberId;
    private String followsFollowState;

}
