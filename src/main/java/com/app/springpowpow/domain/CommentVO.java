package com.app.springpowpow.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Timestamp;

@Component
@Data
//@Schema(description = "회원 정보")
public class CommentVO implements Serializable {

    private Long id;
    private Long memberId;
    private Long postId;
    private String commentContent;
    private Timestamp commentCreateAt;
    private Timestamp commentModifiedAt;

}
