package com.yassine.sport_club_projet.services;

import com.yassine.sport_club_projet.dto.CreateMatchRequestDto;
import com.yassine.sport_club_projet.dto.MatchDto;
import com.yassine.sport_club_projet.dto.UpdateMatchRequestDto;
import com.yassine.sport_club_projet.entites.Match;
import com.yassine.sport_club_projet.exceptions.CoachNotFoundException;
import com.yassine.sport_club_projet.exceptions.MatchNotFoundException;
import com.yassine.sport_club_projet.exceptions.TeamNotFoundException;
import com.yassine.sport_club_projet.mapper.MatchMapper;
import com.yassine.sport_club_projet.repository.CoachRepository;
import com.yassine.sport_club_projet.repository.MatchRepository;
import com.yassine.sport_club_projet.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchService {

    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    private final MatchMapper matchMapper;
    private final CoachRepository coachRepository;

    @Transactional(readOnly = true)
    public List<MatchDto> getAllMatches() {
        return matchRepository.findAllWithTeamsSortByDateTime().stream()
                .map(matchMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public MatchDto getMatchById(Long id) {
        return matchRepository.findById(id)
                .map(matchMapper::toDto)
                .orElseThrow(MatchNotFoundException::new);
    }

    public MatchDto createMatch(CreateMatchRequestDto requestDto) {
        Match match = matchMapper.toEntity(requestDto);
        matchRepository.save(match);
        return matchMapper.toDto(match);
    }

    public MatchDto updateMatchTime(Long id, UpdateMatchRequestDto requestDto) {
        Match match = matchRepository.findById(id).orElseThrow(MatchNotFoundException::new);
        matchMapper.update(requestDto, match);
        Match updatedMatch = matchRepository.save(match);
        return matchMapper.toDto(updatedMatch);
    }

    public void deleteMatch(Long id) {
        if (!matchRepository.existsById(id)) throw new MatchNotFoundException();
        matchRepository.deleteById(id);
    }

    public List<MatchDto> getAllMatchesForCoach(Long coachId) throws CoachNotFoundException {
        var coach = coachRepository.findById(coachId).orElse(null);
        if(coach == null)
            throw new CoachNotFoundException();
        return matchRepository.findAllWithTeamsSortByDateTime().stream()
                .filter(m -> coach.getTeams().contains(m.getTeam()))
                .map(matchMapper::toDto)
                .toList();

    }
}