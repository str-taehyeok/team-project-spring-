package com.app.springpowpow.repository;

import com.app.springpowpow.domain.PostFileVO;
import com.app.springpowpow.mapper.PostFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostFileDAO {
    public final PostFileMapper postFileMapper;

    public void save(PostFileVO postFileVO) {
        postFileMapper.insert(postFileVO);
    }

    public List<PostFileVO> findAll() {
        return postFileMapper.selectAll();
    }
}
