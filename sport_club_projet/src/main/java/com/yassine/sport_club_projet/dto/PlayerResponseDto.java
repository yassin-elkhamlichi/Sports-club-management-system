package com.yassine.sport_club_projet.dto;


import com.yassine.sport_club_projet.entites.User;
import lombok.Data;

import java.time.LocalDate;


@Data
public class PlayerResponseDto {

    private Long user;

    private Integer jerseyNumber;

    private LocalDate birthDate;

    private String position;

    private String medicalCertificate;

    private Double height;

    private Double weight;

    private Long teamId;
}
