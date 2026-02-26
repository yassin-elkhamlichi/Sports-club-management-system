package com.yassine.sport_club_projet.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;


import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
public class TeamDto {

    private Long id;

    private String name;

    private Long categoryId;

    private Long coachId;

    private Set<MatchDto> matches = new LinkedHashSet<>();

    private Set<PlayerResponseDto> players = new LinkedHashSet<>();
}
