package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.DeliveryDTO;
import com.app.springpowpow.domain.DeliveryVO;
import com.app.springpowpow.domain.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeliveryMapper {

//    여긴 배송VO 추가
    public void insert(DeliveryVO deliveryVO);

    public List<DeliveryDTO> selectAll();

    public void update(ProductDTO productDTO);

    public void delete(Long id);
}
