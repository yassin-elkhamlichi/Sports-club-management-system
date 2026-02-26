package com.yassine.sport_club_projet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AttendanceDto {

    private Long id;

    private Boolean isPresent;

    private LocalDateTime checkInTime;

    private Long matchId;

    private Long playerName;
}
