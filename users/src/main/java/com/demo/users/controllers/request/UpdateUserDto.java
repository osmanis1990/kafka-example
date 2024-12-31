package com.demo.users.controllers.request;

public record UpdateUserDto(
        String name,
        String lastName,
        String email) {
}
