package com.app.springpowpow.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PostFileVO {
    private Long id;
    private Long postId;
    private String postFileName;
    private String postFilePath;


}
