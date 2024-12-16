package com.app.springpowpow.service;

import com.app.springpowpow.domain.PostFileVO;

import java.util.List;

public interface PostFileService {

    public void register(PostFileVO postFileVO);

    public List<PostFileVO> getList();
}
