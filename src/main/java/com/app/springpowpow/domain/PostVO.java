package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
@Component
@Data
public class PostVO implements Serializable {

    @Schema(description = "게시물 번호", example = "1", required = true)
    private Long id;
    @Schema(description = "회원 번호", example = "1", required = true)
    private Long memberId;
    @Schema(description = "게시물 내용", example = "오늘의 반려동물컬러를 뭘까요?", required = true)
    private String postContent;
<<<<<<< HEAD
=======
    @Schema(description = "게시물 색상", example = "orange")
>>>>>>> 507af47d77f2728d354ab2a1db9d7ebe704e43c4
    private String postColor;
}
