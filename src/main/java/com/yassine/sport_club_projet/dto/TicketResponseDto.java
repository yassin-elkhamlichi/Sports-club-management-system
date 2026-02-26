package com.yassine.sport_club_projet.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;


import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class TicketResponseDto {

    private Long id;

    private String qrCode;

    private Boolean isUsed;

    private BigDecimal price;

    private MatchDto matchDto;

    private Long memberId;


}
