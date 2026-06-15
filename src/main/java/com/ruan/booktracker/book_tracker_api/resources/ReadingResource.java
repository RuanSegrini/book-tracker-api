package com.ruan.booktracker.book_tracker_api.resources;

import com.ruan.booktracker.book_tracker_api.dto.reading.request.CreateReadingRequest;
import com.ruan.booktracker.book_tracker_api.dto.reading.request.UpdateReadingRequest;
import com.ruan.booktracker.book_tracker_api.dto.reading.response.ReadingResponse;
import com.ruan.booktracker.book_tracker_api.services.ReadingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/readings")
public class ReadingResource {

    private final ReadingService service;

    public ReadingResource(ReadingService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ReadingResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadingResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ReadingResponse> insert(@Valid @RequestBody CreateReadingRequest dto) {
        ReadingResponse response = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReadingResponse> update(@PathVariable UUID id, @Valid @RequestBody UpdateReadingRequest dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
