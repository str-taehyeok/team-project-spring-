package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

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
    @Schema(description = "게시글 이미지", example = "[\"post1.png\", \"post2.png\"]")
    private List<String> postImage;
    @Schema(description = "게시글 색상", example = "Gold")
    private String postColor;
    @Schema(description = "회원 이메일", example = "test1234@test.app")
    private String memberEmail;
    @Schema(description = "회원 이름", example = "아무개")
    private String memberName;
}
