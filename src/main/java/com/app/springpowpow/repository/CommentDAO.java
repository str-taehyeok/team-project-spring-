package com.app.springpowpow.repository;

import com.app.springpowpow.domain.CommentVO;
import com.app.springpowpow.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentDAO {

    private final CommentMapper commentMapper;

    // 댓글 추가
    public void addComment(CommentVO commentVO) {
        commentMapper.insertComment(commentVO);
    }

    // 댓글 수정
    public void updateComment(CommentVO commentVO) {
        commentMapper.updateComment(commentVO);
    }

    // 댓글 삭제
    public void removeComment(Long id) {
        commentMapper.deleteComment(id);
    }

    // 단일 댓글 조회
    public CommentVO getCommentById(Long id) {
        return commentMapper.selectCommentById(id);
    }

    // 전체 댓글 조회
    public List<CommentVO> getCommentsByPostId(Long postId) {
        return commentMapper.selectCommentsByPostId(postId);
    }

}
