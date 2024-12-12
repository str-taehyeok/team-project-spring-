package com.app.springpowpow.repository;

import com.app.springpowpow.domain.ProductLikesDTO;
import com.app.springpowpow.domain.ProductLikesVO;
import com.app.springpowpow.mapper.ProductLikesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductLIkesDAO {

    private final ProductLikesMapper productLikesMapper;

//    좋아요 추가
    public void addLike(ProductLikesVO productLikesVO) {
        productLikesMapper.insert(productLikesVO);
    }

//    좋아요 취소
    public void removeLike(ProductLikesVO productLikesVO) {
        productLikesMapper.delete(productLikesVO);
    }

//    내가 누른 좋아요 제품 보기
    public List<ProductLikesDTO> getLikedProductsByMember(Long memberId) {
        return productLikesMapper.select(memberId);
    }

//    모든 좋아요 기록 조회
    public List<ProductLikesDTO> getAllLikes(Long memberId) {
        return productLikesMapper.selectAll(memberId);
    }
}

