package com.ruan.booktracker.book_tracker_api.services;

import com.ruan.booktracker.book_tracker_api.dto.review.ReviewCreateDTO;
import com.ruan.booktracker.book_tracker_api.dto.review.ReviewDTO;
import com.ruan.booktracker.book_tracker_api.entities.Book;
import com.ruan.booktracker.book_tracker_api.entities.Review;
import com.ruan.booktracker.book_tracker_api.entities.User;
import com.ruan.booktracker.book_tracker_api.exceptions.ResourceNotFoundException;
import com.ruan.booktracker.book_tracker_api.repositories.BookRepository;
import com.ruan.booktracker.book_tracker_api.repositories.ReviewRepository;
import com.ruan.booktracker.book_tracker_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<ReviewDTO> findAll() {
        List<Review> list = repository.findAll();

        return list.stream()
                .map(ReviewDTO::new)
                .toList();
    }

    public ReviewDTO findById(Long id) {
            Review entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

            return new ReviewDTO(entity);
    }

    public ReviewDTO insert(ReviewCreateDTO dto) {

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Book book = bookRepository.findById(dto.bookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        Review entity = new Review();

        entity.setUser(user);
        entity.setBook(book);
        entity.setRating(dto.rating());
        entity.setComment(dto.comment());

        entity = repository.save(entity);

        return new ReviewDTO(entity);
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
