package com.demo.users.controllers.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record CreateUserDto(
        @NotEmpty
        String name,
        @NotEmpty
        String lastName,
        @NotEmpty
        @Email
        String email) {
}
