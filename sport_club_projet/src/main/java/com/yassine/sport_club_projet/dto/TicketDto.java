package com.yassine.sport_club_projet.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class TicketDto {

    private String qrCode;

    private BigDecimal price;

    private Boolean isUsed;


}
