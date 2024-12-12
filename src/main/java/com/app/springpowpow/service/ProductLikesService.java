package com.app.springpowpow.service;

import com.app.springpowpow.domain.ProductLikesDTO;
import com.app.springpowpow.domain.ProductLikesVO;

import java.util.List;

public interface ProductLikesService {

//    좋아요 추가
    public void addLike(ProductLikesVO productLikesVO);

//    좋아요 취소
    public void removeLike(ProductLikesVO productLikesVO);

//    내가 누른 좋아요 게시글 보기
    public List<ProductLikesDTO> getLikedProductsByMember(Long memberId);

//    모든 좋아요 기록 조회
    public List<ProductLikesDTO> getAllLikes(Long memberId);

}
