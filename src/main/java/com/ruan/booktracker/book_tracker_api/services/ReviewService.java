package com.ruan.booktracker.book_tracker_api.services;

import com.ruan.booktracker.book_tracker_api.dto.review.request.CreateReviewRequest;
import com.ruan.booktracker.book_tracker_api.dto.review.request.UpdateReviewRequest;
import com.ruan.booktracker.book_tracker_api.dto.review.response.ReviewResponse;
import com.ruan.booktracker.book_tracker_api.entities.Book;
import com.ruan.booktracker.book_tracker_api.entities.Review;
import com.ruan.booktracker.book_tracker_api.entities.User;
import com.ruan.booktracker.book_tracker_api.exceptions.BusinessRuleException;
import com.ruan.booktracker.book_tracker_api.exceptions.ResourceNotFoundException;
import com.ruan.booktracker.book_tracker_api.repositories.BookRepository;
import com.ruan.booktracker.book_tracker_api.repositories.ReviewRepository;
import com.ruan.booktracker.book_tracker_api.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {

    private final ReviewRepository repository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public ReviewService(ReviewRepository repository, UserRepository userRepository, BookRepository bookRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<ReviewResponse> findAll() {
        return repository.findAll().stream().map(ReviewResponse::new).toList();
    }

    public ReviewResponse findById(UUID id) {
        Review entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review", id));
        return new ReviewResponse(entity);
    }

    public ReviewResponse insert(CreateReviewRequest dto) {
        if (repository.existsByUserIdAndBookId(dto.userId(), dto.bookId())) {
            throw new BusinessRuleException("User has already reviewed this book");
        }

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User", dto.userId()));
        Book book = bookRepository.findById(dto.bookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book", dto.bookId()));

        Review entity = new Review();
        entity.setRating(dto.rating());
        entity.setComment(dto.comment());
        entity.setUser(user);
        entity.setBook(book);

        if (!entity.isValidRating()) {
            throw new BusinessRuleException("Rating must be between 1 and 5");
        }

        entity = repository.save(entity);
        return new ReviewResponse(entity);
    }

    public ReviewResponse update(UUID id, UpdateReviewRequest dto) {
        Review entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review", id));

        entity.setRating(dto.rating());
        entity.setComment(dto.comment());

        if (!entity.isValidRating()) {
            throw new BusinessRuleException("Rating must be between 1 and 5");
        }

        entity = repository.save(entity);
        return new ReviewResponse(entity);
    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Review", id);
        }
        repository.deleteById(id);
    }
}
