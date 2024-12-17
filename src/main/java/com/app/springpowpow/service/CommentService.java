package com.app.springpowpow.service;

import com.app.springpowpow.domain.CommentDTO;
import com.app.springpowpow.domain.CommentVO;

import java.util.List;

public interface CommentService {

    // 댓글 추가
    public void addComment(CommentVO commentVO);

    // 댓글 삭제
    public void removeComment(Long id);

    // 모든 댓글 조회
    public List<CommentDTO> getCommentsByPostId(Long postId);
}
