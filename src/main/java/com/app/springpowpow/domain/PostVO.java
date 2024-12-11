package com.app.springpowpow.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
//@Schema(description = "회원 정보")
public class PostVO implements Serializable {

    private long id;
    private String postTitle;
    private String postContent;
    private long memberId;
    private int postReadCount;

}
