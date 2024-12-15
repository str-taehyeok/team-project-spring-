package com.app.springpowpow.domain;

import lombok.Data;
import java.io.Serializable;
@Data
public class PostVO implements Serializable {

    private Long id;
    private Long memberId;
    private String postContent;
    private String postImage1;
    private String postImage2;
    private String postImage3;
    private String postImage4;
    private String postImage5;
    private String postColor;
}
