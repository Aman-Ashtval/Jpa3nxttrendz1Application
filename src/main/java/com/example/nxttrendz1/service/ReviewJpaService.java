package com.example.nxttrendz1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

import com.example.nxttrendz1.model.Review;
import com.example.nxttrendz1.model.Product;
import com.example.nxttrendz1.repository.ReviewJpaRepository;
import com.example.nxttrendz1.repository.ProductJpaRepository;
import com.example.nxttrendz1.repository.ReviewRepository;

@Service
public class ReviewJpaService implements ReviewRepository {

    @Autowired
    private ReviewJpaRepository reviewJpa;

    @Autowired
    private ProductJpaRepository productJpa;

    @Override
    public ArrayList<Review> getReviews() {
        List<Review> reviewList = reviewJpa.findAll();
        return new ArrayList<>(reviewList);
    }

    @Override
    public Review getReview(int reviewId) {
        try {
            Review review = reviewJpa.findById(reviewId).get();
            return review;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Review addReview(Review review) {
        Product product = review.getProduct();
        int productId = product.getProductId();
        try {
            Product productReview = productJpa.findById(productId).get();
            review.setProduct(productReview);
            reviewJpa.save(review);
            return review;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Review updateReview(int reviewId, Review review) {
        try {
            Review newReview = reviewJpa.findById(reviewId).get();
            if (review.getReviewContent() != null) {
                newReview.setReviewContent(review.getReviewContent());
            }
            if (review.getRating() > 0) {
                newReview.setRating(review.getRating());
            }
            if (review.getProduct() != null) {
                Product product = review.getProduct();
                int productId = product.getProductId();
                Product updatedProduct = productJpa.findById(productId).get();
                newReview.setProduct(updatedProduct);
            }
            reviewJpa.save(newReview);
            return newReview;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteReview(int reviewId) {
        Review review = this.getReview(reviewId);
        if (review != null) {
            reviewJpa.deleteById(reviewId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

}