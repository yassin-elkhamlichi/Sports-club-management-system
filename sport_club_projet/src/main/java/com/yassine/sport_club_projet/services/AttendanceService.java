package com.yassine.sport_club_projet.services;

import com.yassine.sport_club_projet.dto.AttendanceDto;
import com.yassine.sport_club_projet.entites.Attendance;
import com.yassine.sport_club_projet.exceptions.*;
import com.yassine.sport_club_projet.mapper.AttendanceMapper;

import com.yassine.sport_club_projet.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AttendanceService {
    private final AttendanceMapper attendanceMapper;
    private final AttendanceRepository attendanceRepository;
    private final CoachRepository coachRepository;
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;



    public List<AttendanceDto> getAttendanceForMatch(Long id) throws AttendanceNotFoundException {
        var match = matchRepository.findById(id).orElse(null);
        if(match == null)
            throw new MatchNotFoundException();
        var attendance = attendanceRepository.findByMatchId(id).orElse(null);
        if(attendance == null)
            throw new AttendanceNotFoundException();
        return attendance.stream().map(attendanceMapper::toDto).toList();
    }

    public AttendanceDto registerAttendance(Long coachId, Long playerId , Long matchId) throws CoachNotFoundException, PlayerNotFoundException, CoachNotResponseForThisPlayerException {
       var coach = coachRepository.findById(coachId).orElse(null);
       if(coach == null){
           throw new CoachNotFoundException();
       }
       var player = playerRepository.findById(playerId).orElse(null);
       if(player == null){
           throw new PlayerNotFoundException();
       }

       var match = matchRepository.findById(matchId).orElse(null);
       if(match == null){
           throw new MatchNotFoundException();
       }
       if(!coach.findPlayer(player))
           throw new CoachNotResponseForThisPlayerException();


       Attendance attendance = Attendance.builder()
                .isPresent(true)
                .checkInTime(LocalDateTime.now())
                .match(match)
                .player(player)
                .build();
        attendanceRepository.save(attendance);
        return attendanceMapper.toDto(attendance);
    }



    public void deleteAttendance(Long id) throws AttendanceNotFoundException {
        if (!attendanceRepository.existsById(id)) throw new AttendanceNotFoundException();
        attendanceRepository.deleteById(id);
    }

//    public List<AttendanceDto> getAllAttendanceesForCoach(Long coachId) throws CoachNotFoundException {
//        var coach = coachRepository.findById(coachId).orElse(null);
//        if(coach == null)
//            throw new CoachNotFoundException();
//        return attendanceRepository.findAllWithTeamsSortByDateTime().stream()
//                .filter(m -> coach.getTeams().contains(m.getTeam()))
//                .map(attendanceMapper::toDto)
//                .toList();
//
//    }
}
