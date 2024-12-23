package com.app.springpowpow.service;

import com.app.springpowpow.domain.DeliveryDTO;
import com.app.springpowpow.domain.DeliveryVO;
import com.app.springpowpow.domain.ProductDTO;
import com.app.springpowpow.repository.DeliveryDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
    public List<DeliveryDTO> selectAllDeliveryInfo() {
        return deliveryDAO.findAll();
    }

    @Override
    public void updateDeliveryInfo(DeliveryDTO deliveryDTO) {
        deliveryDAO.updateDelivery(deliveryDTO);
    }

    @Override
    public void deleteDeliveryInfo(Long id) {
        deliveryDAO.deleteDelivery(id);
    }
}
