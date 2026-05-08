package com.ruan.booktracker.book_tracker_api.dto;

import com.ruan.booktracker.book_tracker_api.entities.Reading;
import com.ruan.booktracker.book_tracker_api.entities.enums.ReadingStatus;

public class ReadingDTO {

    private Long id;
    private Integer currentPage;
    private ReadingStatus status;

    public ReadingDTO() {
    }

    public ReadingDTO(Long id, Integer currentPage, ReadingStatus status) {
        this.id = id;
        this.currentPage = currentPage;
        this.status = status;
    }

    public ReadingDTO(Reading entity) {
        id = entity.getId();
        currentPage = entity.getCurrentPage();
        status = entity.getStatus();
    }

    public Long getId() {
        return id;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public ReadingStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public void setStatus(ReadingStatus status) {
        this.status = status;
    }
}