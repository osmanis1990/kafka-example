package com.demo.users.controllers.request;

public record CreateUserDto(
        String name,
        String lastName,
        String email) {
}
