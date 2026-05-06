package com.ruan.booktracker.book_tracker_api.repositories;

import com.ruan.booktracker.book_tracker_api.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
