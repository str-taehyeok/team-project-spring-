package com.app.springpowpow.service;

import com.app.springpowpow.domain.BannerDTO;
import com.app.springpowpow.domain.BannerVO;


import java.util.List;
import java.util.Optional;

public interface BannerService {
    public void write(BannerVO bannerVO);

    public List<BannerDTO> getList();

    public Optional<BannerDTO> read(Long id);

    public void edit(BannerVO bannerVO);

    public void remove(Long id);
}
