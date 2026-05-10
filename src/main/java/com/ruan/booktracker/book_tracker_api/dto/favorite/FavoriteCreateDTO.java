package com.ruan.booktracker.book_tracker_api.dto.favorite;

public record FavoriteCreateDTO(

        Long userId,
        Long bookId

) {
}
