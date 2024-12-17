package com.app.springpowpow.controller;

import com.app.springpowpow.domain.CommentVO;
import com.app.springpowpow.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments/*")
public class CommentAPI {

    private final CommentService commentService;

    // 댓글 추가
    @Operation(summary = "댓글 추가", description = "댓글 추가할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "댓글 작성 완료")
    @PostMapping("add")
    public void addComment(@RequestBody CommentVO commentVO) {
        log.info("댓글 추가 성공: {}", commentVO.toString());
        commentService.addComment(commentVO);
    }

    // 댓글 삭제
    @Operation(summary = "댓글 삭제", description = "댓글 삭제할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "댓글 삭제 완료")
    @DeleteMapping("delete/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.removeComment(id);
    }

    // 전체 댓글 조회
    @Operation(summary = "댓글 전체 조회", description = "댓글 전체 조회할 수 있는 API")
    @GetMapping("list")
    public List<CommentVO> getCommentsByPostId(@RequestParam Long postId) {
        return commentService.getCommentsByPostId(postId);
    }
}
