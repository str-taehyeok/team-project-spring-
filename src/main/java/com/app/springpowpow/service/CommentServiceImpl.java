package com.app.springpowpow.service;

import com.app.springpowpow.domain.CommentDTO;
import com.app.springpowpow.domain.CommentVO;
import com.app.springpowpow.repository.CommentDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImpl implements CommentService {

    @Autowired
    private final CommentDAO commentDAO;

    // 댓글 추가
    @Override
    public void addComment(CommentVO commentVO) {
        commentDAO.addComment(commentVO);
    }

    // 댓글 삭제
    @Override
    public void removeComment(Long id) {
        commentDAO.removeComment(id);
    }

    // 댓글 조회
    @Override
    public List<CommentDTO> getCommentsByPostId(Long postId) {
        return commentDAO.getCommentsByPostId(postId);
    }
}
