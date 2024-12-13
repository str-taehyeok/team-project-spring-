package com.app.springpowpow.service;

import com.app.springpowpow.domain.ProductLikesDTO;
import com.app.springpowpow.domain.ProductLikesVO;
import com.app.springpowpow.repository.ProductLIkesDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductLikesServiceImpl implements ProductLikesService {

    @Autowired
    private final ProductLIkesDAO productLIkesDAO;

    @Override
    public void addLike(ProductLikesVO productLikesVO) {
        productLIkesDAO.addLike(productLikesVO);
    }

    @Override
    public void removeLike(ProductLikesVO productLikesVO) {
        productLIkesDAO.removeLike(productLikesVO);
    }

    @Override
    public List<ProductLikesDTO> getLikedProductsByMember(Long memberId) {
        return productLIkesDAO.getLikedProductsByMember(memberId);
    }

    @Override
    public List<ProductLikesDTO> getAllLikes(Long memberId) {
        return productLIkesDAO.getAllLikes(memberId);
    }
}
