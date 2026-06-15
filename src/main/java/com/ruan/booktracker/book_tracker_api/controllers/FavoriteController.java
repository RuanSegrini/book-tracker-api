package com.ruan.booktracker.book_tracker_api.controllers;

import com.ruan.booktracker.book_tracker_api.dto.favorite.request.CreateFavoriteRequest;
import com.ruan.booktracker.book_tracker_api.dto.favorite.response.FavoriteResponse;
import com.ruan.booktracker.book_tracker_api.services.FavoriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/favorites")
public class FavoriteController {

    private final FavoriteService service;

    @GetMapping
    public ResponseEntity<Page<FavoriteResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoriteResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<FavoriteResponse> insert(@Valid @RequestBody CreateFavoriteRequest dto) {
        FavoriteResponse response = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
