package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.DeliveryVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeliveryMapper {

    public void insert(DeliveryVO deliveryVO);

    public void update(DeliveryVO deliveryVO);
}
