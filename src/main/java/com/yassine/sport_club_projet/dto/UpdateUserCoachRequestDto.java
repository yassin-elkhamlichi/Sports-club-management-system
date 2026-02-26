package com.yassine.sport_club_projet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UpdateUserCoachRequestDto {
    private String email;

    private String firstname;

    private String lastname;

    private String phone;

    private String specialization;

    private String certificateLevel;
}
