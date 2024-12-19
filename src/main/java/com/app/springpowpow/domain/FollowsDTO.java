package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "팔로우 정보")
public class FollowsDTO {

    @Schema(description = "ID", example = "1", required = true)
    private Long id;

    @Schema(description = "팔로워 회원 ID", example = "100", required = true)
    private Long followerMemberId;

    @Schema(description = "팔로잉 회원 ID", example = "200", required = true)
    private Long followingMemberId;

    @Schema(description = "팔로우 상태", example = "1", required = true)
    private String followsFollowState;

    @Schema(description = "회원 이메일", example = "test123@naver.com")
    private String memberEmail;

    @Schema(description = "회원 닉네임", example = "징징이")
    private String memberNickname;

    @Schema(description = "회원 이미지파일 경로", example = "default.jpg")
    private String memberFilePath;

}