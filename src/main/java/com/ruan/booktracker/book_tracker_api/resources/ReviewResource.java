package com.ruan.booktracker.book_tracker_api.resources;

import com.ruan.booktracker.book_tracker_api.dto.review.request.CreateReviewRequest;
import com.ruan.booktracker.book_tracker_api.dto.review.request.UpdateReviewRequest;
import com.ruan.booktracker.book_tracker_api.dto.review.response.ReviewResponse;
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
@RequestMapping(value = "/reviews")
public class ReviewResource {

    private final ReviewService service;

    @GetMapping
    public ResponseEntity<Page<ReviewResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ReviewResponse> insert(@Valid @RequestBody CreateReviewRequest dto) {
        ReviewResponse response = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> update(@PathVariable UUID id, @Valid @RequestBody UpdateReviewRequest dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
