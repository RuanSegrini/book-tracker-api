package com.ruan.booktracker.book_tracker_api.resources;


import com.ruan.booktracker.book_tracker_api.dto.user.UserCreateDTO;
import com.ruan.booktracker.book_tracker_api.dto.user.UserDTO;
import com.ruan.booktracker.book_tracker_api.dto.user.UserUpdateDTO;
import com.ruan.booktracker.book_tracker_api.entities.User;
import com.ruan.booktracker.book_tracker_api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }


    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody @Valid UserCreateDTO dto) {
        UserDTO userDTO = service.insert(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userDTO.id())
                .toUri();

        return ResponseEntity.created(uri).body(userDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO dto) {
        UserDTO obj = service.update(id, dto);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
