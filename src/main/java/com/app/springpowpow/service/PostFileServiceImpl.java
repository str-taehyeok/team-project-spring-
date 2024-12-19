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
    public void insertNewImage(PostFileVO postFileVO) {

        postFileDAO.saveImage(postFileVO);
    }

    //    모든 사진 리스트로
    @Override
    public List<PostFileVO> getList() {
        return postFileDAO.findAll();
    }

    @Override
    public void updateImage(PostFileVO productFileVO) {
        postFileDAO.updateImage(productFileVO);
    }

    @Override
    public void deleteImage(Long postId) {
        postFileDAO.deleteAllImage(postId);
    }
}
