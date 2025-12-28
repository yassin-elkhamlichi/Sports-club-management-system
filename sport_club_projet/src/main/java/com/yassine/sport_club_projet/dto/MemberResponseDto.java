package com.yassine.sport_club_projet.dto;

import com.yassine.sport_club_projet.entites.Player;
import com.yassine.sport_club_projet.entites.Subscription;
import com.yassine.sport_club_projet.entites.Ticket;
import com.yassine.sport_club_projet.entites.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    private Set<Subscription> subscriptions = new LinkedHashSet<>();

    private Set<Ticket> tickets = new LinkedHashSet<>();
}
