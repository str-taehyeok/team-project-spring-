package com.app.springpowpow.domain;

import lombok.Data;
import java.io.Serializable;
@Data
public class PostVO implements Serializable {

    private Long id;
    private Long memberId;
    private String postContent;
    private String postImage;
    private String postColor;
}
