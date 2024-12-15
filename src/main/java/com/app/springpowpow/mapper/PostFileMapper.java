package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.PostFileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostFileMapper {
    public void insert (PostFileVO postFileVO);

    public List<PostFileVO> selectAll ();
}
