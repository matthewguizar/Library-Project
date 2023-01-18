package com.example.springbootlibrary.service;

import com.example.springbootlibrary.dao.BookRepository;
import com.example.springbootlibrary.dao.ReviewRepository;
import com.example.springbootlibrary.entity.Book;
import com.example.springbootlibrary.entity.Review;
import com.example.springbootlibrary.requestmodels.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }

    public void postReview(String userEmail, ReviewRequest reviewRequest) throws Exception {
        Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, reviewRequest.getBookId());

        if (validateReview != null){
            throw new Exception("Review already created");
        }

        Review review = new Review();
        review.setRating(reviewRequest.getRating());
        review.setBookId(reviewRequest.getBookId());
        review.setUserEmail(userEmail);

        if (reviewRequest.getReviewDescription().isPresent()){
            review.setReviewDescription(reviewRequest.getReviewDescription().map(
                    Object::toString
            ).orElse(null));
        }
        review.setDate(Date.valueOf(LocalDate.now()));

        reviewRepository.save(review);
    }

    public Boolean userReviewListed(String userEmail, Long bookId){


        Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, bookId);
        if (validateReview != null ){
            return true;
        } else {
            return false;
        }
    }
}
