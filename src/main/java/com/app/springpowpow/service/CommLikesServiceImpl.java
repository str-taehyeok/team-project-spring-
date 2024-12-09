package com.app.springpowpow.service;

import com.app.springpowpow.domain.CommLikesDTO;
import com.app.springpowpow.repository.CommLikesDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CommLikesServiceImpl implements CommLikesService {

    private final CommLikesDAO commLikesDAO;

    @Override
    public void like(CommLikesDTO commLikesDTO) {
        commLikesDAO.save(commLikesDTO);
    }

    @Override
    public List<CommLikesDTO> getList() {
        return commLikesDAO.findAll();
    }

    @Override
    public void remove(Long id) {
        commLikesDAO.delete(id);
    }

}
