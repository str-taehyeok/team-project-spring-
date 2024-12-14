package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {

    public void insert(ReviewDTO reviewDTO);

    public List<ReviewDTO> selectAll(Long productId);

    public void update(ReviewDTO reviewDTO);

    public void delete(Long id);

}
