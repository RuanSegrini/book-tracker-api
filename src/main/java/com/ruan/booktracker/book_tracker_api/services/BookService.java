package com.ruan.booktracker.book_tracker_api.services;

import com.ruan.booktracker.book_tracker_api.dto.book.request.CreateBookRequest;
import com.ruan.booktracker.book_tracker_api.dto.book.request.UpdateBookRequest;
import com.ruan.booktracker.book_tracker_api.dto.book.response.BookResponse;
import com.ruan.booktracker.book_tracker_api.entities.Book;
import com.ruan.booktracker.book_tracker_api.exceptions.ResourceNotFoundException;
import com.ruan.booktracker.book_tracker_api.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<BookResponse> findAll() {
        return repository.findAll().stream().map(BookResponse::new).toList();
    }

    public BookResponse findById(UUID id) {
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", id));
        return new BookResponse(entity);
    }

    public BookResponse insert(CreateBookRequest dto) {
        Book entity = new Book();
        entity.setTitle(dto.title());
        entity.setAuthor(dto.author());
        entity.setTotalPages(dto.totalPages());
        entity.setGenre(dto.genre());
        entity = repository.save(entity);
        return new BookResponse(entity);
    }

    public BookResponse update(UUID id, UpdateBookRequest dto) {
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", id));
        entity.setTitle(dto.title());
        entity.setAuthor(dto.author());
        entity.setTotalPages(dto.totalPages());
        entity.setGenre(dto.genre());
        entity = repository.save(entity);
        return new BookResponse(entity);
    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Book", id);
        }
        repository.deleteById(id);
    }
}
