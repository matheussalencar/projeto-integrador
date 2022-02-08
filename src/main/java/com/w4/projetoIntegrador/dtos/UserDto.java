package com.w4.projetoIntegrador.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.w4.projetoIntegrador.entities.User;

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


    public static UserDto converte(User user) {
        return UserDto.builder()
        .username(user.getUsername())
        .email(user.getEmail())
        .build();
    }


    public static List<UserDto> converte(List<User> users){
		return users.stream().map(u -> converte(u)).collect(Collectors.toList());
	}
}
