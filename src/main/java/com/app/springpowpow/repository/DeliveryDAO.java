package com.app.springpowpow.repository;


import com.app.springpowpow.domain.DeliveryVO;
import com.app.springpowpow.domain.ProductDTO;
import com.app.springpowpow.mapper.DeliveryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeliveryDAO {

    private final DeliveryMapper deliveryMapper;

    public void save(ProductDTO productDTO) {
        deliveryMapper.insert(productDTO);
    }

    public void updateDelivery(ProductDTO productDTO) {
        deliveryMapper.update(productDTO);
    }

    public void deleteDelivery(Long id) {
        deliveryMapper.delete(id);
    }

}
