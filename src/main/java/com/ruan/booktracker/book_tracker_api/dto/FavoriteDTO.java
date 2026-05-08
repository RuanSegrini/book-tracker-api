package com.ruan.booktracker.book_tracker_api.dto;

import com.ruan.booktracker.book_tracker_api.entities.Favorite;

public class FavoriteDTO {

    private Long id;

    public FavoriteDTO() {
    }

    public FavoriteDTO(Long id) {
        this.id = id;
    }

    public FavoriteDTO(Favorite entity) {
        id = entity.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
