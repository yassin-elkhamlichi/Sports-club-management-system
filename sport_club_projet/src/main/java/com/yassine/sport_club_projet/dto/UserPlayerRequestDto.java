package com.yassine.sport_club_projet.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserPlayerRequestDto {

    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String phone;
    private LocalDate birthDate;
    private Integer jerseyNumber;
    private String position;
    private String medicalCertificate;
    private Double height;
    private Double weight;
    private Long teamId;
}
