package com.yassine.sport_club_projet.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDto {

    private LocalDate startDate;

    private LocalDate endDate;

    private String status;

    private Long planId;

    private Long memberId;

}
