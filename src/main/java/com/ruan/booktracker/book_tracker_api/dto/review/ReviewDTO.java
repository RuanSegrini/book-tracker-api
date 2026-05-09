package com.ruan.booktracker.book_tracker_api.dto.review;

import com.ruan.booktracker.book_tracker_api.entities.Review;

public record ReviewDTO(
        Long id,
        Integer rating,
        String comment
) {

    public ReviewDTO(Review entity) {
        this(
                entity.getId(),
                entity.getRating(),
                entity.getComment()
        );
    }
}