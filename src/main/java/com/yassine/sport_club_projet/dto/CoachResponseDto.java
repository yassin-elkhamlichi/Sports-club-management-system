package com.yassine.sport_club_projet.dto;

import com.yassine.sport_club_projet.entites.Team;
import com.yassine.sport_club_projet.entites.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
public class CoachResponseDto {

    private Long id;

    private String specialization;

    private String certificateLevel;

    private Set<TeamDto> teams = new LinkedHashSet<>();
}
