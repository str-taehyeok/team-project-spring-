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
    @Schema(description = "게시글 색상", example = "Gold")
    private String postColor;
    @Schema(description = "회원 이메일", example = "test1234@test.app")
    private String memberEmail;
    @Schema(description = "회원 닉네임", example = "꼰대 류재은")
    private String memberNickName;
    @Schema(description = "회원 이미지 이름", example = "test.jpg")
    private String memberFileName;
    @Schema(description = "회원 이미지 경로", example = "2024/12/17")
    private String memberFilePath;
    @Schema(description = "이미지 이름1", example = "test.jpg")
    private String imageName1;
    @Schema(description = "이미지 경로1", example = "2024/12/17")
    private String imagePath1;
    @Schema(description = "이미지 이름2", example = "test.jpg")
    private String imageName2;
    @Schema(description = "이미지 경로2", example = "2024/12/17")
    private String imagePath2;
    @Schema(description = "이미지 이름3", example = "test.jpg")
    private String imageName3;
    @Schema(description = "이미지 경로3", example = "2024/12/17")
    private String imagePath3;
    @Schema(description = "이미지 이름4", example = "test.jpg")
    private String imageName4;
    @Schema(description = "이미지 경로4", example = "2024/12/17")
    private String imagePath4;
    @Schema(description = "이미지 이름5", example = "test.jpg")
    private String imageName5;
    @Schema(description = "이미지 경로5", example = "2024/12/17")
    private String imagePath5;
    @Schema(description = "좋아요 수", example = "999")
    private int postLikeCount;
}
