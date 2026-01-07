package com.yassine.sport_club_projet.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MatchDto {

    private LocalDateTime dateTime;

    private String opponentName;

    private Integer scoreHome;

    private Integer scoreAway;

    private String result;

    private String facilityName;

    private String teamName;


    private Integer remainingTicket ;
}