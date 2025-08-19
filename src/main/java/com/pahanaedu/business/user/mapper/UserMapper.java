package com.pahanaedu.business.user.mapper;

import com.pahanaedu.business.user.dto.UserDTO;
import com.pahanaedu.business.user.model.User;

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
        dto.setName(user.getName());
        dto.setNicNo(user.getNicNo());
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
                .setName(dto.getName())
                .setNicNo(dto.getNicNo())
                .build();
    }
}
