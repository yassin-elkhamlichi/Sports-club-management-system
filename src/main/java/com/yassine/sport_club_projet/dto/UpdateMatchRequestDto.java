package com.yassine.sport_club_projet.dto;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
public class UpdateMatchRequestDto {
    private Instant newDate;
}