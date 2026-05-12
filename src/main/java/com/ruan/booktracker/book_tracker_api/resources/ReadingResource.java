package com.ruan.booktracker.book_tracker_api.resources;


import com.ruan.booktracker.book_tracker_api.dto.reading.ReadingCreateDTO;
import com.ruan.booktracker.book_tracker_api.dto.reading.ReadingDTO;
import com.ruan.booktracker.book_tracker_api.dto.reading.ReadingUpdateDTO;
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
    public ResponseEntity<List<ReadingDTO>> findAll(){
        List<ReadingDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReadingDTO> findById(@PathVariable Long id) {
        ReadingDTO obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<ReadingDTO> insert(@RequestBody @Valid ReadingCreateDTO dto) {
        ReadingDTO readingDTO = service.insert(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(readingDTO.id())
                .toUri();
        return ResponseEntity.created(uri).body(readingDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ReadingDTO> update(@PathVariable Long id, @RequestBody @Valid ReadingUpdateDTO dto) {
        ReadingDTO obj = service.update(id, dto);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
