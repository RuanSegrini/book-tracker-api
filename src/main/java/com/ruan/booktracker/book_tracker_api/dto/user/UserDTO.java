package com.ruan.booktracker.book_tracker_api.dto.user;

import com.ruan.booktracker.book_tracker_api.entities.User;

public record UserDTO(
        Long id,
        String name,
        String email
) {

    public UserDTO(User entity) {
        this(
                entity.getId(),
                entity.getName(),
                entity.getEmail()
        );
    }
}