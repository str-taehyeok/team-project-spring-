package com.app.springpowpow.service;

import com.app.springpowpow.domain.BannerDTO;
import com.app.springpowpow.domain.BannerVO;
import com.app.springpowpow.repository.BannerDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class BannerServiceImpl implements BannerService {

    private final BannerDAO bannerDAO;

    @Override
    public void write(BannerVO bannerVO) {
        bannerDAO.save(bannerVO);
    }

    @Override
    public List<BannerDTO> getList() {
        return bannerDAO.findAll();
    }

    @Override
    public Optional<BannerDTO> read(Long id) {
        return bannerDAO.findById(id);
    }

    @Override
    public void edit(BannerVO bannerVO) {
        bannerDAO.update(bannerVO);
    }

    @Override
    public void remove(Long id) {
        bannerDAO.delete(id);
    }
}
