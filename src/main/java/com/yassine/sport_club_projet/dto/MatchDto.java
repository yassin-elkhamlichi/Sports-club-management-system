package com.yassine.sport_club_projet.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MatchDto {

    private Long id;

    private LocalDateTime dateTime;

    private String opponentName;

    private Integer scoreHome;

    private Integer scoreAway;

    private String result;

    private String facilityName;

    private String teamName;

    private BigDecimal ticketPrice;


    private Integer remainingTicket ;
}