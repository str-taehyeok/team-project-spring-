package com.app.springpowpow.service;

import com.app.springpowpow.domain.DeliveryDTO;
import com.app.springpowpow.domain.DeliveryVO;
import com.app.springpowpow.domain.ProductDTO;

import java.util.List;

public interface DeliveryService {

    public void insertDeliveryInfo(DeliveryVO deliveryVO);

    public List<DeliveryDTO> selectAllDeliveryInfo();

    public void updateDeliveryInfo(ProductDTO productDTO);

    public void deleteDeliveryInfo(Long id);

}
