package com.yassine.sport_club_projet.dto;

import com.yassine.sport_club_projet.entites.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String token;
    private Long id;
    private String email;
    private Role role;
}
