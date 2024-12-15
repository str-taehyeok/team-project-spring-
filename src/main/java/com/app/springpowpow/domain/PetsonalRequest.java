package com.app.springpowpow.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class PetsonalRequest {
    private PetsonalVO petsonalVO;
    private PetVO petVO;
}
