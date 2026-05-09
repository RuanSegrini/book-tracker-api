package com.ruan.booktracker.book_tracker_api.dto.reading;

import com.ruan.booktracker.book_tracker_api.entities.Reading;
import com.ruan.booktracker.book_tracker_api.entities.enums.ReadingStatus;

public record ReadingDTO(
        Long id,
        Integer currentPage,
        ReadingStatus status
) {

    public ReadingDTO(Reading entity) {
        this(
                entity.getId(),
                entity.getCurrentPage(),
                entity.getStatus()
        );
    }
}