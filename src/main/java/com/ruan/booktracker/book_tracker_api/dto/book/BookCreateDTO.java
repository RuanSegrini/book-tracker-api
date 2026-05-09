package com.ruan.booktracker.book_tracker_api.dto.book;

import com.ruan.booktracker.book_tracker_api.entities.enums.Genre;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BookCreateDTO(

        @NotBlank(message = "Title is required")
        @Size(min = 2, max = 150, message = "Title must be between 2 and 150 characters")
        String title,

        @NotBlank(message = "Author is required")
        @Size(min = 2, max = 100, message = "Author must be between 2 and 100 characters")
        String author,

        @NotNull(message = "Total pages is required")
        @Min(value = 1, message = "Total pages must be at least 1")
        Integer totalPages,

        @NotNull(message = "Genre is required")
        Genre genre
) {
}
