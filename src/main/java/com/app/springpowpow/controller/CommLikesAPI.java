package com.app.springpowpow.controller;

import com.app.springpowpow.domain.CommLikesDTO;
import com.app.springpowpow.domain.CommLikesVO;
import com.app.springpowpow.service.CommLikesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/commLikes/*")
public class CommLikesAPI {

    private final CommLikesService commLikesService;

    // 좋아요 추가
    @Operation(summary = "좋아요 추가", description = "좋아요 추가 API")
    @ApiResponse(responseCode = "200", description = "좋아요 추가 완료")
    @PostMapping("like")
    public void likePost(@RequestBody CommLikesVO commLikesVO) {
        log.info("like {}", commLikesVO.toString());
        commLikesService.addLike(commLikesVO);
    }

    // 좋아요 취소
    @Operation(summary = "좋아요 취소", description = "좋아요 취소 API")
    @ApiResponse(responseCode = "200", description = "좋아요 취소 완료")
    @DeleteMapping("cancelLike")
    public void cancelLike(@RequestBody CommLikesVO commLikesVO) {
        log.info("likeCancel {}", commLikesVO.toString());
        commLikesService.removeLike(commLikesVO);
    }

    // 내가 누른 좋아요 게시글 보기
    @Operation(summary = "좋아요 게시글 조회", description = "좋아요 게시글 단일 조회 API")
    @Parameter(name = "memberId", description = "회원 ID", schema = @Schema(type = "number"), in = ParameterIn.QUERY)
    @GetMapping("likedPosts")
    public List<CommLikesDTO> viewLikedPosts(@RequestParam Long memberId) {
        return commLikesService.getLikedPostsByMember(memberId);
    }

    // 모든 좋아요 기록 조회
    @Operation(summary = "좋아요 전체 조회", description = "좋아요 전체 조회할 수 있는 API")
    @Parameter(name = "memberId", description = "회원 ID", schema = @Schema(type = "number"), in = ParameterIn.QUERY)
    @PostMapping("allLikes")
    public List<CommLikesDTO> viewAllLikes(@RequestBody Long memberId) {
        return commLikesService.getAllLikes(memberId);
    }
}
