package com.ruan.booktracker.book_tracker_api.resources;

import com.ruan.booktracker.book_tracker_api.dto.favorite.response.FavoriteResponse;
import com.ruan.booktracker.book_tracker_api.dto.reading.response.ReadingResponse;
import com.ruan.booktracker.book_tracker_api.dto.review.response.ReviewResponse;
import com.ruan.booktracker.book_tracker_api.dto.user.request.CreateUserRequest;
import com.ruan.booktracker.book_tracker_api.dto.user.request.UpdateUserRequest;
import com.ruan.booktracker.book_tracker_api.dto.user.response.UserResponse;
import com.ruan.booktracker.book_tracker_api.services.FavoriteService;
import com.ruan.booktracker.book_tracker_api.services.ReadingService;
import com.ruan.booktracker.book_tracker_api.services.ReviewService;
import com.ruan.booktracker.book_tracker_api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    private final UserService service;
    private final ReadingService readingService;
    private final ReviewService reviewService;
    private final FavoriteService favoriteService;

    public UserResource(UserService service, ReadingService readingService, ReviewService reviewService, FavoriteService favoriteService) {
        this.service = service;
        this.readingService = readingService;
        this.reviewService = reviewService;
        this.favoriteService = favoriteService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponse> insert(@Valid @RequestBody CreateUserRequest dto) {
        UserResponse response = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable UUID id, @Valid @RequestBody UpdateUserRequest dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/readings")
    public ResponseEntity<List<ReadingResponse>> findReadingsByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(readingService.findByUserId(userId));
    }

    @GetMapping("/{userId}/reviews")
    public ResponseEntity<List<ReviewResponse>> findReviewsByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(reviewService.findByUserId(userId));
    }

    @GetMapping("/{userId}/favorites")
    public ResponseEntity<List<FavoriteResponse>> findFavoritesByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(favoriteService.findByUserId(userId));
    }
}
