package com.yassine.sport_club_projet.dto;

import com.yassine.sport_club_projet.entites.Attendance;
import com.yassine.sport_club_projet.entites.Facility;
import com.yassine.sport_club_projet.entites.Team;
import com.yassine.sport_club_projet.entites.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@Data
public class CreateMatchRequestDto {

    private LocalDateTime dateTime;

    private String opponentName;

    private Integer scoreHome;

    private Integer scoreAway;

    private String result;

    private String  facilityName;

    private String teamName;


}
