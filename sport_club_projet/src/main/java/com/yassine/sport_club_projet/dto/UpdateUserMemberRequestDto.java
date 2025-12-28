package com.yassine.sport_club_projet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UpdateUserMemberRequestDto {
    private String email;
    private String firstname;
    private String lastname;
    private String phone;
    private LocalDate birthDate;
}
