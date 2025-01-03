package com.demo.users.configurations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Topic {
    USER_CREATED("user-created");

    final String value;
}
