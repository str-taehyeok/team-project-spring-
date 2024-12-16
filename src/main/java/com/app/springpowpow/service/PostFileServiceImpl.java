package com.app.springpowpow.service;

import com.app.springpowpow.domain.PostFileVO;
import com.app.springpowpow.repository.PostFileDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class PostFileServiceImpl implements PostFileService {
    private final PostFileDAO postFileDAO;

    @Override
    public void register(PostFileVO postFileVO) {
        postFileDAO.save(postFileVO);
    }

    @Override
    public List<PostFileVO> getList() {
        return postFileDAO.findAll();
    }
}
