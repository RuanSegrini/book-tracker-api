package com.ruan.booktracker.book_tracker_api.dto;

import com.ruan.booktracker.book_tracker_api.entities.Review;

public class ReviewDTO {

    private Long id;
    private Integer rating;
    private String comment;

    public ReviewDTO() {
    }

    public ReviewDTO(Long id, Integer rating, String comment) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
    }

    public ReviewDTO(Review entity) {
        id = entity.getId();
        rating = entity.getRating();
        comment = entity.getComment();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
