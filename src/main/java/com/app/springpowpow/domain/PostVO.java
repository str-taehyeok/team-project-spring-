package com.app.springpowpow.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Timestamp;

@Component
@Data
//@Schema(description = "회원 정보")
public class PostVO implements Serializable {

    private long id;
    private long memberId;
    private String postTitle;
    private String postContent;
    private Timestamp postCreateAt;
    private Timestamp postModifiedAt;
    private String postImage1;
    private String postImage2;
    private String postImage3;
    private String postImage4;
    private String postImage5;

}
