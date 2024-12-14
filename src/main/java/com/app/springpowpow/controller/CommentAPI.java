package com.app.springpowpow.controller;

import com.app.springpowpow.domain.CommentVO;
import com.app.springpowpow.service.CommentService;
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
    private final CommentVO commentVO;

    // 댓글 추가
    @PostMapping("add")
    public void addComment(@RequestBody CommentVO commentVO) {
        commentService.addComment(commentVO);
//        log.info("댓글 추가 성공: {}", commentVO);
    }

    // 댓글 수정
    @PutMapping("update")
    public void updateComment(@RequestBody CommentVO commentVO) {
        commentService.updateComment(commentVO);
//        log.info("댓글 수정 성공: {}", commentVO);
    }

    // 댓글 삭제
    @DeleteMapping("delete")
    public void deleteComment(@RequestBody Long commentId) {
        commentService.removeComment(commentId);
//        log.info("댓글 삭제 성공: {}", commentId);
    }

    // 전체 댓글 조회
    @PostMapping("post")
    public List<CommentVO> getCommentsByPostId(@RequestBody Long postId) {
        return commentService.getCommentsByPostId(postId);
    }
}
