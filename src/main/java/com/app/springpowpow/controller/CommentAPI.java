package com.app.springpowpow.controller;

import com.app.springpowpow.domain.CommentVO;
import com.app.springpowpow.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentAPI {

    private final CommentService commentService;
    private final CommentVO commentVO;

    // 댓글 추가
    @PostMapping("/add")
    public void addComment(@RequestBody CommentVO commentVO) {
        commentService.addComment(commentVO);
//        log.info("댓글 추가 성공: {}", commentVO);
    }

    // 댓글 수정
    @PostMapping("/update")
    public void updateComment(@RequestBody CommentVO commentVO) {
        commentService.updateComment(commentVO);
//        log.info("댓글 수정 성공: {}", commentVO);
    }

    // 댓글 삭제
    @PostMapping("/delete")
    public void deleteComment(@RequestParam Long commentId) {
        commentService.removeComment(commentId);
//        log.info("댓글 삭제 성공: {}", commentId);
    }

    // 단일 댓글 조회
    @GetMapping("/get")
    public Optional<CommentVO> getCommentById(@RequestParam Long id) {
        return commentService.getCommentById(id);
    }

    // 전체 댓글 조회
    @GetMapping("/post")
    public List<CommentVO> getCommentsByPostId(@RequestParam Long postId) {
        return commentService.getCommentsByPostId(postId);
    }
}
