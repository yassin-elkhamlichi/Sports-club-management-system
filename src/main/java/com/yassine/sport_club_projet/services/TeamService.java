package com.yassine.sport_club_projet.services;

import com.yassine.sport_club_projet.dto.CoachResponseDto;
import com.yassine.sport_club_projet.entites.Team;
import com.yassine.sport_club_projet.exceptions.PlayerNotFoundException;
import com.yassine.sport_club_projet.exceptions.TeamNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeamService {
    private final PlayerService playerService;

    public Team playerToTeam(Long teamId , Long playerId) throws PlayerNotFoundException, TeamNotFoundException {
        return playerService.assignPlayerToTeam(teamId ,playerId);
    }

}
