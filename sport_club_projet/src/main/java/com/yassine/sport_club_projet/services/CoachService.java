package com.yassine.sport_club_projet.services;

import com.fasterxml.jackson.core.Base64Variant;
import com.yassine.sport_club_projet.dto.*;
import com.yassine.sport_club_projet.entites.Role;
import com.yassine.sport_club_projet.entites.Team;
import com.yassine.sport_club_projet.exceptions.*;
import com.yassine.sport_club_projet.mapper.CoachMapper;
import com.yassine.sport_club_projet.mapper.UserMapper;

import com.yassine.sport_club_projet.repositories.CoachRepository;
import com.yassine.sport_club_projet.repositories.PlayerRepository;
import com.yassine.sport_club_projet.repositories.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CoachService {
    public final CoachRepository coachRepository;
    private final CoachMapper coachMapper;
    private final UserMapper userMapper;
    private final TeamService teamService;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;

    public List<CoachResponseDto> GetAllCoachs() {
                return coachRepository.findAllWithTeamsAndPlayers().stream().map(coachMapper::toDto).toList();
    }

    public CoachResponseDto GetCoach(Long id) throws CoachNotFoundException {
        var coach = coachRepository.findCoachByIdWithTeamsAndPlayers(id);
        if(coach.isEmpty()){
            throw new CoachNotFoundException();
        }
        return coachMapper.toDto(coach.get());
    }

    public CoachResponseDto AddCoach(UserCoachRequestDto userCoachRequestDto) {
        var coach = coachMapper.toEntity(userCoachRequestDto);
        var user = userMapper.toEntity(userCoachRequestDto);
        String password = passwordEncoder.encode(userCoachRequestDto.getPassword());
        user.setPassword(password);
        user.setRole(Role.COACH);
        coach.setUser(user);
        coachRepository.save(coach);
        return coachMapper.toDto(coach);
    }

    public CoachResponseDto updateCoach(Long id, UpdateUserCoachRequestDto updateUserCoachRequestDto) throws CoachNotFoundException {
        var coach = coachRepository.findCoachByIdWithTeamsAndPlayers(id).orElse(null);
        if(coach == null){
            throw new CoachNotFoundException();
        }
        UpdateCoachDto coachUpdate = coachMapper.toDto(updateUserCoachRequestDto);
        UpdateUserRequestDto userUpdate = userMapper.toDto(updateUserCoachRequestDto);
        coachMapper.update(coachUpdate, coach);
        userMapper.update(userUpdate, coach.getUser());
        coachRepository.save(coach);
        return coachMapper.toDto(coach);
    }

    public void deleteCoach(Long id) throws CoachNotFoundException {
        var coach = coachRepository.findCoachByIdWithTeamsAndPlayers(id).orElse(null);
        if(coach == null){
            throw new CoachNotFoundException();
        }
        coachRepository.deleteById(id);
    }


    public CoachResponseDto assignTeamToCoach(Long coachId, Long teamId) throws CoachNotFoundException, TeamNotFoundException, TeamAlreadyExistException {
        var coach = coachRepository.findCoachByIdWithTeamsAndPlayers(coachId).orElse(null);
        if(coach == null)
            throw new CoachNotFoundException();
        Team team1 = teamRepository.findById(teamId).orElse(null);
        if(team1 == null)
            throw new TeamNotFoundException();
        if(coach.findTeam(team1.getId()))
            throw new TeamAlreadyExistException();

        team1.setCoach(coach);
        coach.getTeams().add(team1);

        teamRepository.save(team1);

        return coachMapper.toDto(coach);
    }
}
