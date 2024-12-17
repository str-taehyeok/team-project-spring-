package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.CommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CommentMapper {

    // 댓글 추가
    public void insertComment(CommentVO commentVO);

    // 댓글 삭제
    public void deleteComment(Long id);

    // 전체 댓글 조회
    public List<CommentVO> selectAllComment(Long postId);

}
