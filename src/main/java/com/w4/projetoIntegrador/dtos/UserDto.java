package com.w4.projetoIntegrador.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.w4.projetoIntegrador.entities.User;
import com.w4.projetoIntegrador.enums.ProfileTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String username;
    private String email;
    private boolean enabled;
    private ProfileTypes profileTypes;


    public static UserDto converteToUserDto(User user) {
        return UserDto.builder()
        .username(user.getUsername())
        .email(user.getEmail())
        .enabled(user.isActive())
        .profileTypes(user.getProfileType())
        .build();
    }


    public static List<UserDto> converte(List<User> users){
		return users.stream().map(u -> converteToUserDto(u)).collect(Collectors.toList());
	}
}
