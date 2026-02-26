package com.yassine.sport_club_projet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateCoachDto {
    private String specialization;

    private String certificateLevel;
}
