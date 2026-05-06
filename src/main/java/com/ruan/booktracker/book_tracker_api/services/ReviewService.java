package com.ruan.booktracker.book_tracker_api.services;

import com.ruan.booktracker.book_tracker_api.entities.Review;
import com.ruan.booktracker.book_tracker_api.exceptions.ResourceNotFoundException;
import com.ruan.booktracker.book_tracker_api.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    public List<Review> findAll() {
        return repository.findAll();
    }

    public Review findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Review insert(Review obj) {

        if (obj.getUser() == null || obj.getBook() == null) {
            throw new RuntimeException("Review must have a user and a book");
        }

        if (obj.getRating() == null || obj.getRating() < 1 || obj.getRating() > 5) {
            throw new RuntimeException("Rating must be between 1 and 5");
        }

        return repository.save(obj);
    }

    public Review update(Long id, Review obj) {
        Review entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(Review entity, Review obj) {
        entity.setRating(obj.getRating());
        entity.setComment(obj.getComment());
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
