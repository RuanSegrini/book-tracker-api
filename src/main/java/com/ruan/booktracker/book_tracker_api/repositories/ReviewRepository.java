package com.ruan.booktracker.book_tracker_api.repositories;

import com.ruan.booktracker.book_tracker_api.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {

    boolean existsByUserIdAndBookId(UUID userId, UUID bookId);

    List<Review> findByUserId(UUID userId);

    List<Review> findByBookId(UUID bookId);
}
