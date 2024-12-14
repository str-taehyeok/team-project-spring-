package com.app.springpowpow.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Component
public class CommLikesDTO implements Serializable {

    private Long id;
    private Long memberId;
    private Long postId;
    private String postContent;
    private String postImage1;
    private String postImage2;
    private String postImage3;
    private String postImage4;
    private String postImage5;

}
