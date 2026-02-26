package com.yassine.sport_club_projet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserRequestDto {
    private String email;
    private String firstname;
    private String lastname;
    private String phone;
}
