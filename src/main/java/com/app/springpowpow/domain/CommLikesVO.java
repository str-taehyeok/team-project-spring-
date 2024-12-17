package com.app.springpowpow.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Component
public class CommLikesVO {
    private Long id;
    private Long memberId;
    private Long postId;
}
