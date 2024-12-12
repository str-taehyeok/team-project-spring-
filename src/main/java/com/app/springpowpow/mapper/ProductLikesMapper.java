package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.ProductLikesDTO;
import com.app.springpowpow.domain.ProductLikesVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductLikesMapper {

//    좋아요 추가
    public void insert(ProductLikesVO productLikesVO);

//    좋아요 취소
    public void delete(ProductLikesVO productLikesVO);

//    내가 누른 좋아요 제품 보기
    public List<ProductLikesDTO> select(Long memberId);

//    모든 좋아요 기록 조회 (특정 회원의 좋아요 기록 조회)
    public List<ProductLikesDTO> selectAll(Long memberId);
}
