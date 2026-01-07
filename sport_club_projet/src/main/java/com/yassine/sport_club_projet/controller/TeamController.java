package com.yassine.sport_club_projet.controller;

import com.yassine.sport_club_projet.dto.TeamDto;
import com.yassine.sport_club_projet.dto.errorMessageDto;
import com.yassine.sport_club_projet.exceptions.PlayerNotFoundException;
import com.yassine.sport_club_projet.exceptions.TeamNotFoundException;
import com.yassine.sport_club_projet.mapper.TeamMapper;
import com.yassine.sport_club_projet.services.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Team")
@AllArgsConstructor
public class TeamController {
    private final TeamService teamService;
    private final TeamMapper teamMapper;
    @PostMapping("/{teamId}/player/{playerId}")
    public ResponseEntity<TeamDto> assignPToT(
            @PathVariable Long teamId,
            @PathVariable Long playerId
    ) throws PlayerNotFoundException, TeamNotFoundException {
        var teamResponse = teamService.playerToTeam(teamId , playerId);
        return ResponseEntity.ok(teamMapper.toDto(teamResponse));
    }


}
