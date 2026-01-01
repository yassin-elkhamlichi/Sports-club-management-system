package com.yassine.sport_club_projet.services;

import com.yassine.sport_club_projet.dto.*;
import com.yassine.sport_club_projet.exceptions.CoachNotFoundException;
import com.yassine.sport_club_projet.mapper.CoachMapper;
import com.yassine.sport_club_projet.mapper.UserMapper;
import com.yassine.sport_club_projet.repository.CoachRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CoachService {
    public final CoachRepository coachRepository;
    private final CoachMapper coachMapper;
    private UserMapper userMapper;

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
        user.setRole("Coach");
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
}
