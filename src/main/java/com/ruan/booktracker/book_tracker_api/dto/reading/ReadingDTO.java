package com.ruan.booktracker.book_tracker_api.dto.reading;

import com.ruan.booktracker.book_tracker_api.entities.Reading;
import com.ruan.booktracker.book_tracker_api.entities.enums.ReadingStatus;

import java.time.LocalDateTime;
public record ReadingDTO(

        Long id,
        Integer currentPage,
        ReadingStatus status,
        LocalDateTime startedAt,
        LocalDateTime finishedAt,
        Long userId,
        Long bookId

) {

    public ReadingDTO(Reading entity) {
        this(
                entity.getId(),
                entity.getCurrentPage(),
                entity.getStatus(),
                entity.getStartedAt(),
                entity.getFinishedAt(),
                entity.getUser().getId(),
                entity.getBook().getId()
        );
    }
}