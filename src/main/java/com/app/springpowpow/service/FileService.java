package com.app.springpowpow.service;

import com.app.springpowpow.domain.FileVO;

import java.util.List;

public interface FileService {
    public void register(FileVO fileVO);

    public List<FileVO> getList();
}
