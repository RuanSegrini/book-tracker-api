package com.ruan.booktracker.book_tracker_api.services;

import com.ruan.booktracker.book_tracker_api.dto.user.request.CreateUserRequest;
import com.ruan.booktracker.book_tracker_api.dto.user.request.UpdateUserRequest;
import com.ruan.booktracker.book_tracker_api.dto.user.response.UserResponse;
import com.ruan.booktracker.book_tracker_api.entities.User;
import com.ruan.booktracker.book_tracker_api.exceptions.ResourceNotFoundException;
import com.ruan.booktracker.book_tracker_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository repository;

    public Page<UserResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(UserResponse::new);
    }

    public UserResponse findById(UUID id) {
        User entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
        return new UserResponse(entity);
    }

    @Transactional
    public UserResponse insert(CreateUserRequest dto) {
        User entity = new User();
        entity.setName(dto.name());
        entity.setEmail(dto.email());
        entity.setPassword(dto.password());
        entity = repository.save(entity);
        return new UserResponse(entity);
    }

    @Transactional
    public UserResponse update(UUID id, UpdateUserRequest dto) {
        User entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
        entity.setName(dto.name());
        entity.setEmail(dto.email());
        entity = repository.save(entity);
        return new UserResponse(entity);
    }

    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("User", id);
        }
        repository.deleteById(id);
    }
}
