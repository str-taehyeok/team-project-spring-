package com.app.springpowpow.service;

import com.app.springpowpow.domain.ReviewDTO;
import com.app.springpowpow.repository.ReviewDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDAO reviewDAO;
    @Override
    public void insertReview(ReviewDTO reviewDTO) {
        reviewDAO.save(reviewDTO);
    }

    @Override
    public List<ReviewDTO> getReviews(Long productId) {
        return reviewDAO.findAll(productId);
    }

    @Override
    public void updateReview(ReviewDTO reviewDTO) {
        reviewDAO.updateReview(reviewDTO);
    }

    @Override
    public void deleteReview(Long id) {
        reviewDAO.deleteReview(id);
    }
}
