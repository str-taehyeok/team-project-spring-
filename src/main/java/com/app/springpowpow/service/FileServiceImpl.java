package com.app.springpowpow.service;

import com.app.springpowpow.domain.FileVO;
import com.app.springpowpow.repository.FileDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl implements FileService {

    private final FileDAO fileDAO;

    @Override
    public void register(FileVO fileVO) {
        fileDAO.save(fileVO);
    }

    @Override
    public List<FileVO> getList() {
        return fileDAO.findAll();
    }
}
