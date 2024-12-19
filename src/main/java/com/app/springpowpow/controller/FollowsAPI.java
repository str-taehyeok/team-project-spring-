package com.app.springpowpow.controller;

import com.app.springpowpow.domain.FollowsDTO;
import com.app.springpowpow.domain.FollowsVO;
import com.app.springpowpow.service.FollowsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/follows/*")
public class FollowsAPI {

    private final FollowsService followsService;

    // 팔로우 추가
    @Operation(summary = "팔로우 추가", description = "팔로우 추가 API")
    @ApiResponse(responseCode = "200", description = "팔로우 추가 완료")
    @PostMapping("add")
    public void addFollow(@RequestBody FollowsVO followsVO) {
//        log.info("팔로우 추가 요청: followerMemberId = {}, followingMemberId = {}, followsFollowState = {}",
//        followsVO.getFollowerMemberId(), followsVO.getFollowingMemberId(), followsVO.getFollowsFollowState());
        followsService.addFollow(followsVO);
    }

    // 팔로우 취소
    @Operation(summary = "팔로우 취소", description = "팔로우 취소 API")
    @ApiResponse(responseCode = "200", description = "팔로우 취소 완료")
    @DeleteMapping("cancel")
    public void removeFollow(@RequestBody FollowsVO followsVO) {
        followsService.removeFollow(followsVO);
    }

    // 팔로워 리스트 조회
    @Operation(summary = "팔로워 리스트 조회", description = "특정 유저의 팔로워 리스트 조회 API")
    @ApiResponse(responseCode = "200", description = "팔로워 리스트 조회 완료")
    @Parameter(name = "memberId", description = "회원 ID", schema = @Schema(type = "number"))
    @GetMapping("followers/{memberId}")
    public List<FollowsDTO> getFollowerList(@PathVariable Long memberId) {
        return followsService.getFollowerList(memberId);
    }

    // 팔로잉 리스트 조회
    @Operation(summary = "팔로잉 리스트 조회", description = "특정 유저의 팔로잉 리스트 조회 API")
    @ApiResponse(responseCode = "200", description = "팔로잉 리스트 조회 완료")
    @Parameter(name = "memberId", description = "회원 ID", schema = @Schema(type = "number"))
    @GetMapping("following/{memberId}")
    public List<FollowsDTO> getFollowingList(@PathVariable Long memberId) {
        return followsService.getFollowingList(memberId);
    }
}