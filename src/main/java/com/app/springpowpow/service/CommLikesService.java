package com.app.springpowpow.service;

import com.app.springpowpow.domain.CommLikesDTO;

import java.util.List;

public interface CommLikesService {

    public void like(CommLikesDTO commLikesDTO);

    public List<CommLikesDTO> getList();

    public void remove(Long id);


}
