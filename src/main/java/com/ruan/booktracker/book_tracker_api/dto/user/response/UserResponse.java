package com.ruan.booktracker.book_tracker_api.dto.user.response;

import com.ruan.booktracker.book_tracker_api.entities.User;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email,
        LocalDateTime createdAt
) {
    public UserResponse(User entity) {
        this(entity.getId(), entity.getName(), entity.getEmail(), entity.getCreatedAt());
    }
}
