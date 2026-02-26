package com.yassine.sport_club_projet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class MemberResponseDto {

    private Long id;

    private Long user;

    private LocalDate birthDate;

    private Integer loyaltyPoints;

    private Boolean isActive;

    private Set<SubscriptionDto> subscriptions = new LinkedHashSet<>();

    private Set<TicketResponseDto> ticketsId = new LinkedHashSet<>();
}
