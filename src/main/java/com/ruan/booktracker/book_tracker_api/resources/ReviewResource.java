package com.ruan.booktracker.book_tracker_api.resources;


import com.ruan.booktracker.book_tracker_api.dto.review.ReviewCreateDTO;
import com.ruan.booktracker.book_tracker_api.dto.review.ReviewDTO;
import com.ruan.booktracker.book_tracker_api.dto.review.ReviewUpdateDTO;
import com.ruan.booktracker.book_tracker_api.entities.Review;
import com.ruan.booktracker.book_tracker_api.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewResource {

    @Autowired
    private ReviewService service;

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> findAll() {
        List<ReviewDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReviewDTO> findById(@PathVariable Long id) {
        ReviewDTO obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> insert(@RequestBody @Valid ReviewCreateDTO dto) {
        ReviewDTO reviewDTO = service.insert(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reviewDTO.id())
                .toUri();
        return ResponseEntity.created(uri).body(reviewDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ReviewDTO> update(@PathVariable Long id, @RequestBody @Valid ReviewUpdateDTO dto) {
        ReviewDTO obj = service.update(id, dto);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
