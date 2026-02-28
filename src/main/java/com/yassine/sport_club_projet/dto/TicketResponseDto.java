package com.yassine.sport_club_projet.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Setter
public class TicketResponseDto {

    private Long id;

    private String qrCode;

    private Boolean isUsed;

    private BigDecimal price;

    private MatchDto matchDto;

    private Long memberId;


    public TicketResponseDto() {

    }
}
