package com.ruan.booktracker.book_tracker_api.dto.review.response;

import com.ruan.booktracker.book_tracker_api.entities.Review;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReviewResponse(
        UUID id,
        Integer rating,
        String comment,
        LocalDateTime createdAt,
        UUID userId,
        UUID bookId
) {
    public ReviewResponse(Review entity) {
        this(entity.getId(), entity.getRating(), entity.getComment(),
             entity.getCreatedAt(), entity.getUser().getId(), entity.getBook().getId());
    }
}
