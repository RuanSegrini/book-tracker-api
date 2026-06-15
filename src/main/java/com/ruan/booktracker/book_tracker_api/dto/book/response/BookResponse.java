package com.ruan.booktracker.book_tracker_api.dto.book.response;

import com.ruan.booktracker.book_tracker_api.entities.Book;
import com.ruan.booktracker.book_tracker_api.entities.enums.BookGenre;

import java.time.LocalDateTime;
import java.util.UUID;

public record BookResponse(
        UUID id,
        String title,
        String author,
        Integer totalPages,
        BookGenre genre,
        LocalDateTime createdAt
) {
    public BookResponse(Book entity) {
        this(entity.getId(), entity.getTitle(), entity.getAuthor(),
             entity.getTotalPages(), entity.getGenre(), entity.getCreatedAt());
    }
}
