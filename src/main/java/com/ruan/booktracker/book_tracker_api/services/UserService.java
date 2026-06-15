package com.ruan.booktracker.book_tracker_api.services;

import com.ruan.booktracker.book_tracker_api.dto.user.request.CreateUserRequest;
import com.ruan.booktracker.book_tracker_api.dto.user.request.UpdateUserRequest;
import com.ruan.booktracker.book_tracker_api.dto.user.response.UserResponse;
import com.ruan.booktracker.book_tracker_api.entities.User;
import com.ruan.booktracker.book_tracker_api.exceptions.ResourceNotFoundException;
import com.ruan.booktracker.book_tracker_api.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserResponse> findAll() {
        return repository.findAll().stream().map(UserResponse::new).toList();
    }

    public UserResponse findById(UUID id) {
        User entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
        return new UserResponse(entity);
    }

    public UserResponse insert(CreateUserRequest dto) {
        User entity = new User();
        entity.setName(dto.name());
        entity.setEmail(dto.email());
        entity.setPassword(dto.password());
        entity = repository.save(entity);
        return new UserResponse(entity);
    }

    public UserResponse update(UUID id, UpdateUserRequest dto) {
        User entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
        entity.setName(dto.name());
        entity.setEmail(dto.email());
        entity = repository.save(entity);
        return new UserResponse(entity);
    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("User", id);
        }
        repository.deleteById(id);
    }
}
