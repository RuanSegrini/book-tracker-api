package com.ruan.booktracker.book_tracker_api.dto.review.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateReviewRequest(
        @NotNull @Min(1) @Max(5) Integer rating,
        @NotBlank String comment,
        @NotNull UUID userId,
        @NotNull UUID bookId
) {}
