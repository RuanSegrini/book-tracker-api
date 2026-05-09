package com.ruan.booktracker.book_tracker_api.dto.book;

import com.ruan.booktracker.book_tracker_api.entities.Book;
import com.ruan.booktracker.book_tracker_api.entities.enums.Genre;

import java.time.LocalDateTime;

public record BookDTO(
        Long id,
        String title,
        String author,
        Integer totalPages,
        Genre genre,
        LocalDateTime createdAt
) {

    public BookDTO(Book entity) {
        this(
                entity.getId(),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getTotalPages(),
                entity.getGenre(),
                entity.getCreatedAt()
        );
    }
}