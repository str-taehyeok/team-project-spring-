package com.app.springpowpow.repository;

import com.app.springpowpow.domain.CommentDTO;
import com.app.springpowpow.domain.CommentVO;
import com.app.springpowpow.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentDAO {

    private final CommentMapper commentMapper;

    // 댓글 추가
    public void addComment(CommentVO commentVO) {
        commentMapper.insertComment(commentVO);
    }

    // 댓글 삭제
    public void removeComment(Long id) {
        commentMapper.deleteComment(id);
    }

    // 전체 댓글 조회
    public List<CommentDTO> getCommentsByPostId(Long postId) {
        return commentMapper.selectAllComment(postId);
    }

}
