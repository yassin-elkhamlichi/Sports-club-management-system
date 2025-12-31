package com.yassine.sport_club_projet.controller;

import com.yassine.sport_club_projet.dto.*;
import com.yassine.sport_club_projet.exceptions.PlayerNotFoundException;
import com.yassine.sport_club_projet.exceptions.UserAlreadyExistException;
import com.yassine.sport_club_projet.services.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Player")
@AllArgsConstructor
public class PlayerController {
    public final PlayerService playerService;

    @GetMapping()
    public ResponseEntity<List<PlayerResponseDto>> GetAllPlayers(){
        return ResponseEntity.ok(playerService.GetAllPlayers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponseDto> GetPlayer(
            @PathVariable Long id
    ) throws PlayerNotFoundException {
        return ResponseEntity.ok(playerService.GetPlayer(id));
    }

    @PostMapping()
    public PlayerResponseDto addPlayer(
            @RequestBody UserPlayerRequestDto userPlayerRequestDto
    ) throws UserAlreadyExistException {
        return playerService.AddPlayer(userPlayerRequestDto);
    }
    @PutMapping("{id}")
    public ResponseEntity<PlayerResponseDto> updatePlayer(
            @PathVariable Long id,
            @RequestBody UpdateUserPlayerRequestDto updateUserPlayerRequestDto
    ) throws PlayerNotFoundException {
        var Player = playerService.updatePlayer(id, updateUserPlayerRequestDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Player);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePlayer(
            @PathVariable Long id
    ) throws PlayerNotFoundException {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<?> handlePlayerException(){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new errorMessageDto("Player not found"));
    }
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<?> handleUserExistException(){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new errorMessageDto("User already exist"));
    }
}
