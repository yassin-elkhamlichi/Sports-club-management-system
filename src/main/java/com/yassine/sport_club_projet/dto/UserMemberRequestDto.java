package com.yassine.sport_club_projet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserMemberRequestDto {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String phone;
    private String role;
    private String birthDate;
}
