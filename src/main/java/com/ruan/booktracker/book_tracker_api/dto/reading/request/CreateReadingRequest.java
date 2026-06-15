package com.ruan.booktracker.book_tracker_api.dto.reading.request;

import com.ruan.booktracker.book_tracker_api.entities.enums.ReadingStatus;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateReadingRequest(
        @NotNull Integer currentPage,
        @NotNull ReadingStatus status,
        @NotNull UUID userId,
        @NotNull UUID bookId
) {}
