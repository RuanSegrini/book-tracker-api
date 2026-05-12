package com.ruan.booktracker.book_tracker_api.dto.reading;

import com.ruan.booktracker.book_tracker_api.entities.enums.ReadingStatus;
import jakarta.validation.constraints.NotNull;

public record ReadingUpdateDTO(

        @NotNull(message = "Current page is required")
        Integer currentPage,

        @NotNull(message = "Status is required")
        ReadingStatus status

) {
}
