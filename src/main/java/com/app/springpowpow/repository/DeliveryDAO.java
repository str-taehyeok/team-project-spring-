package com.app.springpowpow.repository;


import com.app.springpowpow.domain.DeliveryVO;
import com.app.springpowpow.mapper.DeliveryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeliveryDAO {

    private final DeliveryMapper deliveryMapper;

    public void save(DeliveryVO deliveryVO) {
        deliveryMapper.insert(deliveryVO);
    }

    public void updateDelivery(DeliveryVO deliveryVO) {
        deliveryMapper.update(deliveryVO);
    }

}
