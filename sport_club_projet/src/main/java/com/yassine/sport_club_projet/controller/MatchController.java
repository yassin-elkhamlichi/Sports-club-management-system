package com.yassine.sport_club_projet.controller;

import com.yassine.sport_club_projet.dto.CreateMatchRequestDto;
import com.yassine.sport_club_projet.dto.MatchDto;
import com.yassine.sport_club_projet.dto.UpdateMatchRequestDto;
import com.yassine.sport_club_projet.exceptions.CoachNotFoundException;
import com.yassine.sport_club_projet.services.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;



    @GetMapping("/{id}")
    public ResponseEntity<MatchDto> getMatchById(@PathVariable Long id) {
        MatchDto match = matchService.getMatchById(id);
        return ResponseEntity.ok(match);
    }

    @PostMapping
    public ResponseEntity<MatchDto> createMatch(@RequestBody CreateMatchRequestDto createMatchRequestDto) {
        MatchDto newMatch = matchService.createMatch(createMatchRequestDto);
        return new ResponseEntity<>(newMatch, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchDto> updateMatch(@PathVariable Long id, @RequestBody UpdateMatchRequestDto updateMatchRequestDto) {
        MatchDto updatedMatch = matchService.updateMatchTime(id, updateMatchRequestDto);
        return ResponseEntity.ok(updatedMatch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/calendar")
    public ResponseEntity<List<MatchDto>> getCalendar() {
        List<MatchDto> matches = matchService.getAllMatches();
        return ResponseEntity.ok(matches);
    }
    @GetMapping("/calendar/coach")
    public ResponseEntity<List<MatchDto>> getCalendarForCoach(@RequestParam Long coachId) throws CoachNotFoundException {
        List<MatchDto> matches = matchService.getAllMatchesForCoach(coachId);
        return ResponseEntity.ok(matches);
    }
}
