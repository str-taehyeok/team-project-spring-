package com.app.springpowpow.service;

import com.app.springpowpow.domain.NoticeDTO;
import com.app.springpowpow.domain.NoticeVO;

import java.util.List;
import java.util.Optional;

public interface NoticeService {

    public void write(NoticeVO noticeVO);

    public List<NoticeDTO> getList();

    public Optional<NoticeDTO> read(Long id);

    public void edit(NoticeVO noticeVO);

    public void remove(Long id);

}
