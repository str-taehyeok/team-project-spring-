package com.app.springpowpow.repository;

import com.app.springpowpow.domain.ProductDTO;
import com.app.springpowpow.domain.ReviewDTO;
import com.app.springpowpow.mapper.ProductMapper;
import com.app.springpowpow.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReviewDAO {

        private final ReviewMapper reviewMapper;

//        insert review
    public void save(ReviewDTO reviewDTO) {
        reviewMapper.insert(reviewDTO);
    }

    public List<ReviewDTO> findAll(Long productId) {
        return reviewMapper.selectAll(productId);
    }

    public void updateReview(ReviewDTO reviewDTO) {
        reviewMapper.update(reviewDTO);
    }

    public void deleteReview(Long id) {
        reviewMapper.delete(id);
    }

    public void deleteAll(Long productId) {
        reviewMapper.deleteAll(productId);
    }
    }

