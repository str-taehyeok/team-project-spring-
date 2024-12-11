package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
@Data
public class NoticeDTO implements Serializable {
    private Long id;
    private String noticeTitle;
    private String noticeContent;
    private Date noticeDate;
    private Long noticeCount;
    private String MemberEmail;
    private String MemberPassword;

}
