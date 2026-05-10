package com.ruan.booktracker.book_tracker_api.dto.reading;

import com.ruan.booktracker.book_tracker_api.entities.enums.ReadingStatus;
import jakarta.validation.constraints.NotNull;

public record ReadingCreateDTO(

        @NotNull(message = "Current page is required")
        Integer currentPage,

        @NotNull(message = "Status is required")
        ReadingStatus status,

        @NotNull(message = "User id is required")
        Long userId,

        @NotNull(message = "Book id is required")
        Long bookId

) {
}