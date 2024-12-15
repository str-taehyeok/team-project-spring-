package com.app.springpowpow.service;

import com.app.springpowpow.domain.NoticeDTO;
import com.app.springpowpow.domain.NoticeVO;
import com.app.springpowpow.repository.NoticeDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class NoticeServiceImpl implements NoticeService {

    private final NoticeDAO noticeDAO;

    @Override
    public void write(NoticeVO noticeVO) {
        noticeDAO.save(noticeVO);
    }

    @Override
    public List<NoticeDTO> getList() {
        return noticeDAO.findAll();
    }

    @Override
    public Optional<NoticeDTO> read(Long id) {
        return noticeDAO.findById(id);
    }

    @Override
    public void edit(NoticeVO noticeVO) {
        noticeDAO.update(noticeVO);
    }

    @Override
    public void remove(Long id) {
        noticeDAO.delete(id);
    }
}
