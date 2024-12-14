package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.DeliveryVO;
import com.app.springpowpow.domain.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeliveryMapper {

//    여긴 배송VO 추가
    public void insert(DeliveryVO deliveryVO);

    public void update(ProductDTO productDTO);

    public void delete(Long id);
}
