package com.ruan.booktracker.book_tracker_api.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserUpdateDTO(

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email
) {
}
