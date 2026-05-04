package com.ruan.booktracker.book_tracker_api.resources;


import com.ruan.booktracker.book_tracker_api.entities.Reading;
import com.ruan.booktracker.book_tracker_api.services.ReadingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/readings")
public class ReadingResource {

    @Autowired
    private ReadingService service;

    @GetMapping
    public ResponseEntity<List<Reading>> findAll(){
        List<Reading> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Reading> findById(@PathVariable Long id) {
        Reading obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Reading> insert(@RequestBody @Valid Reading obj) {
        obj = service.insert(obj);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Reading> update(@PathVariable Long id, @RequestBody @Valid Reading obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
