package com.ruan.booktracker.book_tracker_api.dto;

import com.ruan.booktracker.book_tracker_api.entities.Book;
import com.ruan.booktracker.book_tracker_api.entities.enums.Genre;

public class BookDTO {

    private Long id;
    private String title;
    private String author;
    private Integer totalPages;
    private Genre genre;

    public BookDTO() {
    }

    public BookDTO(Long id, String title, String author, Integer totalPages, Genre genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.totalPages = totalPages;
        this.genre = genre;
    }

    public BookDTO(Book entity) {
        id = entity.getId();
        title = entity.getTitle();
        author = entity.getAuthor();
        totalPages = entity.getTotalPages();
        genre = entity.getGenre();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
