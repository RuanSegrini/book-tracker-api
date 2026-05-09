package com.ruan.booktracker.book_tracker_api.dto.favorite;

import com.ruan.booktracker.book_tracker_api.entities.Favorite;

public record FavoriteDTO(
        Long id
) {

    public FavoriteDTO(Favorite entity) {
        this(
                entity.getId()
        );
    }
}