package com.app.springpowpow.service;

import com.app.springpowpow.domain.DeliveryVO;
import com.app.springpowpow.domain.ProductDTO;
import com.app.springpowpow.repository.DeliveryDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryDAO deliveryDAO;


    @Override
    public void insertDeliveryInfo(DeliveryVO deliveryVO) {
        deliveryDAO.save(deliveryVO);
    }

    @Override
    public void updateDeliveryInfo(ProductDTO productDTO) {
        deliveryDAO.updateDelivery(productDTO);
    }

    @Override
    public void deleteDeliveryInfo(Long id) {
        deliveryDAO.deleteDelivery(id);
    }
}
