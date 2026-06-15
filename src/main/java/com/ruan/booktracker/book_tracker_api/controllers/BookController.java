package com.ruan.booktracker.book_tracker_api.controllers;

import com.ruan.booktracker.book_tracker_api.dto.book.request.CreateBookRequest;
import com.ruan.booktracker.book_tracker_api.dto.book.request.UpdateBookRequest;
import com.ruan.booktracker.book_tracker_api.dto.book.response.BookResponse;
import com.ruan.booktracker.book_tracker_api.dto.reading.response.ReadingResponse;
import com.ruan.booktracker.book_tracker_api.dto.review.response.ReviewResponse;
import com.ruan.booktracker.book_tracker_api.services.BookService;
import com.ruan.booktracker.book_tracker_api.services.ReadingService;
import com.ruan.booktracker.book_tracker_api.services.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/books")
public class BookController {

    private final BookService service;
    private final ReadingService readingService;
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<Page<BookResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<BookResponse> insert(@Valid @RequestBody CreateBookRequest dto) {
        BookResponse response = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(@PathVariable UUID id, @Valid @RequestBody UpdateBookRequest dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{bookId}/readings")
    public ResponseEntity<List<ReadingResponse>> findReadingsByBookId(@PathVariable UUID bookId) {
        return ResponseEntity.ok(readingService.findByBookId(bookId));
    }

    @GetMapping("/{bookId}/reviews")
    public ResponseEntity<List<ReviewResponse>> findReviewsByBookId(@PathVariable UUID bookId) {
        return ResponseEntity.ok(reviewService.findByBookId(bookId));
    }
}
