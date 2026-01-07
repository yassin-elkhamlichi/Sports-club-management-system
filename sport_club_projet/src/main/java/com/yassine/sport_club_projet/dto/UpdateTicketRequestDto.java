package com.yassine.sport_club_projet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class UpdateTicketRequestDto {

    private BigDecimal price;

    private Boolean isUsed;

    private Long matchId;

    private Long memberId;
}
