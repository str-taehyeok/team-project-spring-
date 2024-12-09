package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.CommLikesDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommLikesMapper {

    public void insert(CommLikesDTO commLikesDTO);

    public List<CommLikesDTO> selectAll();

    public void delete(Long id);

}
