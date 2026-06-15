package com.ruan.booktracker.book_tracker_api.dto.reading.request;

import com.ruan.booktracker.book_tracker_api.entities.enums.ReadingStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateReadingRequest(
        @NotNull Integer currentPage,
        @NotNull ReadingStatus status
) {}
