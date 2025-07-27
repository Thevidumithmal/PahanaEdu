package com.pahanaedu.mapper;

import com.pahanaedu.dto.UserDTO;
import com.pahanaedu.model.User;

public class UserMapper {

    public static UserDTO toDto(User user) {
        if (user == null) return null;
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());
        dto.setPhone(user.getPhone());
        dto.setAddress(user.getAddress());
        return dto;
    }

    public static User toEntity(UserDTO dto) {
        if (dto == null) return null;
        return new User.Builder()
                .setId(dto.getId())
                .setUsername(dto.getUsername())
                .setPassword(dto.getPassword())
                .setRole(dto.getRole())
                .setPhone(dto.getPhone())
                .setAddress(dto.getAddress())
                .build();
    }
}
