package com.demo.users.services.impl;

import com.demo.users.configurations.Topic;
import com.demo.users.controllers.request.CreateUserDto;
import com.demo.users.controllers.request.UpdateUserDto;
import com.demo.users.controllers.response.UserDto;
import com.demo.users.repositories.UserRepository;
import com.demo.users.services.UserService;
import com.demo.users.services.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public UserDto create(CreateUserDto dto) {
        UserDto user = Optional.ofNullable(userMapper.toModel(dto))
                .map(userRepository::save)
                .map(userMapper::toDto)
                .orElse(null);

        for (int i = 0; i < 20000000; i++) {
            kafkaTemplate.send(Topic.USER_CREATED.getValue(), user);
        }

        return user;
    }

    @Override
    public void update(UUID id, UpdateUserDto dto) {
        userRepository.findById(id)
                .map(user -> userMapper.partialUpdate(user, dto))
                .ifPresent(userRepository::save);
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserDto> getById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toDto);
    }

    @Override
    public List<UserDto> getAll() {
        return userMapper.toDto(userRepository.findAll());
    }
}
