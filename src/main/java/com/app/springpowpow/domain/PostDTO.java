package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
@Data
@Schema(description = "게시글 정보")
public class PostDTO {
    @Schema(description = "게시글 번호", example = "1", required = true)
    private Long id;
    @Schema(description = "회원 번호", example = "1", required = true)
    private Long memberId;
    @Schema(description = "게시글 내용", example = "테스트 내용1", required = true)
    private String postContent;
    @Schema(description = "게시글 이미지1", example = "post.png")
    private String postImage1;
    @Schema(description = "게시글 이미지2", example = "post.png")
    private String postImage2;
    @Schema(description = "게시글 이미지3", example = "post.png")
    private String postImage3;
    @Schema(description = "게시글 이미지4", example = "post.png")
    private String postImage4;
    @Schema(description = "게시글 이미지5", example = "post.png")
    private String postImage5;
    @Schema(description = "게시글 색상", example = "Gold")
    private String postColor;
    @Schema(description = "회원 이메일", example = "test1234@test.app")
    private String memberEmail;
    @Schema(description = "회원 이름", example = "아무개")
    private String memberName;
}
