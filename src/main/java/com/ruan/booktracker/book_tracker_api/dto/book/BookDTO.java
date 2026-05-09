package com.ruan.booktracker.book_tracker_api.dto.book;

import com.ruan.booktracker.book_tracker_api.entities.Book;
import com.ruan.booktracker.book_tracker_api.entities.enums.Genre;

public record BookDTO(
        Long id,
        String title,
        String author,
        Integer totalPages,
        Genre genre
) {

    public BookDTO(Book entity) {
        this(
                entity.getId(),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getTotalPages(),
                entity.getGenre()
        );
    }
}