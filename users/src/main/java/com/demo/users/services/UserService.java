package com.demo.users.services;

import com.demo.users.controllers.request.CreateUserDto;
import com.demo.users.controllers.request.UpdateUserDto;
import com.demo.users.controllers.response.UserDto;
import com.demo.users.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    UserDto create(CreateUserDto dto);

    void update(UUID id, UpdateUserDto dto);

    void delete(UUID id);

    Optional<UserDto> getById(UUID id);

    List<UserDto> getAll();
}
