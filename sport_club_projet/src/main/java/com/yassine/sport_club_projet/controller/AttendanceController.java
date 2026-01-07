package com.yassine.sport_club_projet.controller;

import com.yassine.sport_club_projet.dto.AttendanceDto;
import com.yassine.sport_club_projet.exceptions.AttendanceNotFoundException;
import com.yassine.sport_club_projet.exceptions.CoachNotFoundException;
import com.yassine.sport_club_projet.exceptions.CoachNotResponseForThisPlayerException;
import com.yassine.sport_club_projet.exceptions.PlayerNotFoundException;
import com.yassine.sport_club_projet.services.AttendanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendances")
@AllArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;
    
    @GetMapping("/match/{id}")
    public ResponseEntity<List<AttendanceDto>> getAttendanceForMatch(@PathVariable Long id) throws AttendanceNotFoundException {
        return ResponseEntity.ok( attendanceService.getAttendanceForMatch(id));
    }

    @PostMapping("coach/{coachId}/player/{playerId}/match/{matchId}")
    public ResponseEntity<AttendanceDto> createAttendance(
            @PathVariable long coachId,
            @PathVariable long playerId,
            @PathVariable long matchId

    ) throws CoachNotFoundException, CoachNotResponseForThisPlayerException, PlayerNotFoundException {
        AttendanceDto newAttendance = attendanceService.registerAttendance(coachId, playerId , matchId);
        return new ResponseEntity<>(newAttendance, HttpStatus.CREATED);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) throws AttendanceNotFoundException {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }


}
