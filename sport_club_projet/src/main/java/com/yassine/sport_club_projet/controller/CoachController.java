package com.yassine.sport_club_projet.controller;

import com.yassine.sport_club_projet.dto.CoachResponseDto;
import com.yassine.sport_club_projet.dto.UpdateUserCoachRequestDto;
import com.yassine.sport_club_projet.dto.UserCoachRequestDto;
import com.yassine.sport_club_projet.dto.errorMessageDto;
import com.yassine.sport_club_projet.exceptions.CoachNotFoundException;
import com.yassine.sport_club_projet.exceptions.UserAlreadyExistException;
import com.yassine.sport_club_projet.services.CoachService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coach")
@AllArgsConstructor
public class CoachController {

    public final CoachService coachService;

    @GetMapping()
    public ResponseEntity<List<CoachResponseDto>> GetAllCoachs(){
        return ResponseEntity.ok(coachService.GetAllCoachs());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CoachResponseDto> GetCoach(
            @PathVariable Long id
    ) throws CoachNotFoundException {
        return ResponseEntity.ok(coachService.GetCoach(id));
    }

    @PostMapping()
    public CoachResponseDto addCoach(
            @RequestBody UserCoachRequestDto userCoachRequestDto
    ) throws UserAlreadyExistException {
        return coachService.AddCoach(userCoachRequestDto);
    }
    @PutMapping("{id}")
    public ResponseEntity<CoachResponseDto> updateCoach(
            @PathVariable Long id,
            @RequestBody UpdateUserCoachRequestDto updateUserCoachRequestDto
    ) throws CoachNotFoundException {
        var coach = coachService.updateCoach(id, updateUserCoachRequestDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(coach);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCoach(
            @PathVariable Long id
    ) throws CoachNotFoundException {
        coachService.deleteCoach(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(CoachNotFoundException.class)
    public ResponseEntity<?> handleCoachException(){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new errorMessageDto("Coach not found"));
    }
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<?> handleUserExistException(){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new errorMessageDto("User already exist"));
    }
}
