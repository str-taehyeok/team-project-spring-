package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    public void insert (FileVO fileVO);

    public List<FileVO> selectAll();
}
