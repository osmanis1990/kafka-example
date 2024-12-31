package com.demo.users.services.mapper;

import com.demo.users.controllers.request.CreateUserDto;
import com.demo.users.controllers.request.UpdateUserDto;
import com.demo.users.controllers.response.UserDto;
import com.demo.users.models.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User toModel(CreateUserDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(@MappingTarget User user, UpdateUserDto dto);

    UserDto toDto(User user);

    List<UserDto> toDto(List<User> users);
}
