package com.demo.users.controllers.request;

import jakarta.validation.constraints.Email;

public record UpdateUserDto(
        String name,
        String lastName,
        @Email
        String email) {
}
