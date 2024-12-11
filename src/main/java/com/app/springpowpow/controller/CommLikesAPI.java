package com.app.springpowpow.controller;

import com.app.springpowpow.domain.CommLikesDTO;
import com.app.springpowpow.domain.PostVO;
import com.app.springpowpow.service.CommLikesService;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @PostMapping("/like")
    public void likePost(@RequestBody CommLikesDTO commLikesDTO) {
        commLikesService.addLike(commLikesDTO);
    }

    // 좋아요 취소
    @PostMapping("/cancelLike")
    public void cancelLike(@RequestBody CommLikesDTO commLikesDTO) {
        commLikesService.removeLike(commLikesDTO);
    }

    // 내가 누른 좋아요 게시글 보기
    @GetMapping("/likedPosts")
    public List<PostVO> viewLikedPosts(@RequestParam Long memberId) {
        return commLikesService.getLikedPostsByMember(memberId);
    }

    // 모든 좋아요 기록 조회
    @GetMapping("/allLikes")
    public List<CommLikesDTO> viewAllLikes() {
        return commLikesService.getAllLikes();
    }
}
