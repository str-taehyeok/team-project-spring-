package com.app.springpowpow.comment;

import com.app.springpowpow.domain.CommentDTO;
import com.app.springpowpow.mapper.CommentMapper;
import com.app.springpowpow.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class CommentMapperTests {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentService commentService;

    @Test
    public void testSelectAllComments() {

        commentService.getCommentsByPostId(2L).stream().map(CommentDTO::toString).forEach(log::info);
    }
}
