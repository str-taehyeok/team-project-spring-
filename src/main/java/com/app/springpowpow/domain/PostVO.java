package com.app.springpowpow.domain;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;  // LocalDateTime으로 변경

@Data
public class PostVO implements Serializable {

    private Long id;
    private Long memberId;
    private String postTitle;
    private String postContent;
    private LocalDateTime postCreateAt;
    private LocalDateTime postModifiedAt;
    private String postImage1;
    private String postImage2;
    private String postImage3;
    private String postImage4;
    private String postImage5;
}
