package com.ruan.booktracker.book_tracker_api.dto.favorite.response;

import com.ruan.booktracker.book_tracker_api.entities.Favorite;

import java.time.LocalDateTime;
import java.util.UUID;

public record FavoriteResponse(
        UUID id,
        LocalDateTime createdAt,
        UUID userId,
        UUID bookId
) {
    public FavoriteResponse(Favorite entity) {
        this(entity.getId(), entity.getCreatedAt(),
             entity.getUser().getId(), entity.getBook().getId());
    }
}
