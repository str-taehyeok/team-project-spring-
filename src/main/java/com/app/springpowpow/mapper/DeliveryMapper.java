package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.DeliveryVO;
import com.app.springpowpow.domain.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeliveryMapper {

    public void insert(ProductDTO productDTO);

    public void update(ProductDTO productDTO);

    public void delete(Long id);
}
