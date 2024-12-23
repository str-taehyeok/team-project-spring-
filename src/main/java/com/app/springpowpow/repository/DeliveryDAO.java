package com.app.springpowpow.repository;


import com.app.springpowpow.domain.DeliveryDTO;
import com.app.springpowpow.domain.DeliveryVO;
import com.app.springpowpow.domain.ProductDTO;
import com.app.springpowpow.mapper.DeliveryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DeliveryDAO {

    private final DeliveryMapper deliveryMapper;

//    여기도 배송 vo추가
    public void save(DeliveryVO deliveryVO) {
        deliveryMapper.insert(deliveryVO);
    }

    public List<DeliveryDTO> findAll() {
        return deliveryMapper.selectAll();
    }

    public void updateDelivery(DeliveryDTO deliveryDTO) {
        deliveryMapper.update(deliveryDTO);
    }

    public void deleteDelivery(Long id) {
        deliveryMapper.delete(id);
    }

    public void deleteAll(Long productId) {
        deliveryMapper.deleteAll(productId);
    }
}
