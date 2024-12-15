package com.app.springpowpow.repository;

import com.app.springpowpow.domain.FileVO;
import com.app.springpowpow.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FileDAO {
    private final FileMapper fileMapper;

    public void save(FileVO fileVO) {
        fileMapper.insert(fileVO);
    }

    public List<FileVO> findAll() {
        return fileMapper.selectAll();
    }
}
