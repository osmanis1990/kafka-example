package com.demo.users.controllers.request;

public record CreateAddressDto(
        String street,
        String city,
        String state) {
}
