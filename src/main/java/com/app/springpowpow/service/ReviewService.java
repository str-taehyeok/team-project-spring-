package com.app.springpowpow.service;

import com.app.springpowpow.domain.ProductDTO;
import com.app.springpowpow.domain.ReviewDTO;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    public void insertReview(ReviewDTO reviewDTO);
    public List<ReviewDTO> getReviews(Long productId);
    public void updateReview(ReviewDTO reviewDTO);
    public void deleteReview(Long id);

}
