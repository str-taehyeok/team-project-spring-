package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "제품 이미지 정보")
public class ProductFileVO {
    @Schema(description = "이미지 번호", example = "1", required = true)
    private Long id;
    @Schema(description = "제품 번호", example = "21", required = true)
    private Long productId;
    @Schema(description = "사진 명", example = "image.jpg")
    private String productFileName;
    @Schema(description = "사진 경로", example = "image.jpg")
    private String productFilePath;

}
