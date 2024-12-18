package com.app.springpowpow.controller;

import com.app.springpowpow.domain.FollowsVO;
import com.app.springpowpow.service.FollowsService;
import io.swagger.v3.oas.annotations.Operation;
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
        followsService.addFollow(followsVO);
    }

    // 팔로우 취소
    @Operation(summary = "팔로우 취소", description = "팔로우 취소 API")
    @ApiResponse(responseCode = "200", description = "팔로우 취소 완료")
    @DeleteMapping("cancelLike")
    public void removeFollow(@RequestBody FollowsVO followsVO) {
        followsService.removeFollow(followsVO);
    }

    // 팔로워 리스트 조회
    @Operation(summary = "팔로워 리스트 조회", description = "특정 유저의 팔로워 리스트 조회 API")
    @ApiResponse(responseCode = "200", description = "팔로워 리스트 조회 완료")
    @GetMapping("followers/{memberId}")
    public List<FollowsVO> getFollowerList(@PathVariable Long memberId) {
        return followsService.getFollowerList(memberId);
    }

    // 팔로잉 리스트 조회
    @Operation(summary = "팔로잉 리스트 조회", description = "특정 유저의 팔로잉 리스트 조회 API")
    @ApiResponse(responseCode = "200", description = "팔로잉 리스트 조회 완료")
    @GetMapping("following/{memberId}")
    public List<FollowsVO> getFollowingList(@PathVariable Long memberId) {
        return followsService.getFollowingList(memberId);
    }

}