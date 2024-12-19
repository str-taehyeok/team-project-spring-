package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "게시글 이미지 정보")
public class PostFileVO {

    @Schema(description = "파일 번호", example = "1", required = true)
    private Long id;
    @Schema(description = "게시글 번호", example = "21", required = true)
    private Long postId;
    @Schema(description = "사진 명", example = "image.jpg")
    private String postFileName;
    @Schema(description = "사진 경로", example = "image.jpg")
    private String postFilePath;


}
