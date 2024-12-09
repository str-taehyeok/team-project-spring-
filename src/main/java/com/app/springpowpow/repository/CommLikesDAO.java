package com.app.springpowpow.repository;

import com.app.springpowpow.domain.CommLikesDTO;
import com.app.springpowpow.mapper.CommLikesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class CommLikesDAO {

    private final CommLikesMapper commLikesMapper;

    public void save(CommLikesDTO commLikesDTO) {
        commLikesMapper.insert(commLikesDTO);
    }

    public List<CommLikesDTO> findAll() {
        return commLikesMapper.selectAll();
    }

    public void delete(Long id) {
        commLikesMapper.delete(id);
    }
}
