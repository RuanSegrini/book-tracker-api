package com.ruan.booktracker.book_tracker_api.dto.reading.response;

import com.ruan.booktracker.book_tracker_api.entities.Reading;
import com.ruan.booktracker.book_tracker_api.entities.enums.ReadingStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReadingResponse(
        UUID id,
        Integer currentPage,
        ReadingStatus status,
        LocalDateTime startedAt,
        LocalDateTime finishedAt,
        UUID userId,
        UUID bookId
) {
    public ReadingResponse(Reading entity) {
        this(entity.getId(), entity.getCurrentPage(), entity.getStatus(),
             entity.getStartedAt(), entity.getFinishedAt(),
             entity.getUser().getId(), entity.getBook().getId());
    }
}
