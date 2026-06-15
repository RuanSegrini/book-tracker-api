package com.ruan.booktracker.book_tracker_api.resources;

import com.ruan.booktracker.book_tracker_api.dto.favorite.request.CreateFavoriteRequest;
import com.ruan.booktracker.book_tracker_api.dto.favorite.response.FavoriteResponse;
import com.ruan.booktracker.book_tracker_api.services.FavoriteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/favorites")
public class FavoriteResource {

    private final FavoriteService service;

    public FavoriteResource(FavoriteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<FavoriteResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
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
