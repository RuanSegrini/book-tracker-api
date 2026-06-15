package com.ruan.booktracker.book_tracker_api.dto.book.request;

import com.ruan.booktracker.book_tracker_api.entities.enums.BookGenre;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateBookRequest(
        @NotBlank String title,
        @NotBlank String author,
        @NotNull @Min(1) Integer totalPages,
        @NotNull BookGenre genre
) {}
