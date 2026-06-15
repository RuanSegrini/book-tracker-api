package com.ruan.booktracker.book_tracker_api.dto.favorite.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateFavoriteRequest(
        @NotNull UUID userId,
        @NotNull UUID bookId
) {}
