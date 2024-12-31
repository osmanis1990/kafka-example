package com.demo.users.controllers.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDto(
        UUID id,
        String name,
        String lastName,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
