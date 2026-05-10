package com.ruan.booktracker.book_tracker_api.dto.favorite;

import jakarta.validation.constraints.NotNull;

public record FavoriteCreateDTO(

        @NotNull(message = "User id is required")
        Long userId,

        @NotNull(message = "Book id is required")
        Long bookId

) {
}
