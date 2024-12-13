package com.app.springpowpow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BannerDTO {
    private Long id;
    private Long memberId;
    private String bannerTitle;
    private String bannerType;
    private String bannerLink;
    private String bannerStart;
    private String bannerEnd;
    private String bannerImage;
}
