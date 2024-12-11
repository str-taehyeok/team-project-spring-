package com.app.springpowpow.controller;

import com.app.springpowpow.domain.CommLikesDTO;
import com.app.springpowpow.domain.PostVO;
import com.app.springpowpow.service.CommLikesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/community/api/*")
public class CommLikesAPI {

    private final CommLikesService commLikesService;

    // 좋아요 추가

    @Operation(summary = "좋아요 추가", description = "좋아요 추가 API")
    @ApiResponse(responseCode = "200", description = "좋아요 추가 완료")
    @PostMapping("/like")
    public void likePost(@RequestBody CommLikesDTO commLikesDTO) {
        commLikesService.addLike(commLikesDTO);
    }

    // 좋아요 취소
    @Operation(summary = "좋아요 취소", description = "좋아요 취소 API")
    @ApiResponse(responseCode = "200", description = "좋아요 취소 완료")
    @PostMapping("/cancelLike")
    public void cancelLike(@RequestBody CommLikesDTO commLikesDTO) {
        commLikesService.removeLike(commLikesDTO);
    }

    // 내가 누른 좋아요 게시글 보기
    @Operation(summary = "좋아요 게시글 조회", description = "좋아요 게시글 단일 조회 API")
    @Parameter( name = "id", description = "게시글 번호", schema = @Schema(type="number"), in = ParameterIn.HEADER)
    @GetMapping("/likedPosts/{id}")
    public List<PostVO> viewLikedPosts(@RequestParam Long memberId) {
        return commLikesService.getLikedPostsByMember(memberId);
    }

    // 모든 좋아요 기록 조회
    @Operation(summary = "좋아요 전체 조회", description = "좋아요 전체 조회할 수 있는 API")
    @Parameters({
            @Parameter( name = "id", description = "번호", schema = @Schema(type="number"), in = ParameterIn.HEADER, required = true ),
            @Parameter( name = "memberId", description = "멤버 아이디", schema = @Schema(type="number"), in = ParameterIn.HEADER ),
            @Parameter( name = "postId", description = "포스트 아이디", schema = @Schema(type="number"), in = ParameterIn.HEADER),
            @Parameter( name = "commLikesCount", description = "포스트 카운트", schema = @Schema(type="number"), in = ParameterIn.HEADER ),
    })
    @GetMapping("/allLikes")
    public List<CommLikesDTO> viewAllLikes() {
        return commLikesService.getAllLikes();
    }
}
