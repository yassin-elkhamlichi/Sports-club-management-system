package com.yassine.sport_club_projet.controller;

import com.yassine.sport_club_projet.dto.TicketDto;
import com.yassine.sport_club_projet.dto.TicketRequestDto;
import com.yassine.sport_club_projet.dto.UpdateTicketRequestDto;
import com.yassine.sport_club_projet.exceptions.*;
import com.yassine.sport_club_projet.dto.errorMessageDto;
import com.yassine.sport_club_projet.services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@AllArgsConstructor
public class TicketController {

    public final TicketService ticketService;

    @GetMapping()
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getTicket(
            @PathVariable Long id
    ) throws TicketNotFoundException {
        return ResponseEntity.ok(ticketService.getTicket(id));
    }

//    @PostMapping()
//    public ResponseEntity<TicketDto> addTicket(
//            @RequestBody TicketRequestDto ticketRequestDto
//    ) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.addTicket(ticketRequestDto));
//    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDto> updateTicket(
            @PathVariable Long id,
            @RequestBody UpdateTicketRequestDto updateTicketRequestDto
    ) throws TicketNotFoundException {
        var ticket = ticketService.updateTicket(id, updateTicketRequestDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ticket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(
            @PathVariable Long id
    ) throws TicketNotFoundException {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/purchase/member/{memberId}/match/{matchId}")
    public ResponseEntity<TicketDto> purchaseTicket(
            @PathVariable Long memberId,
            @PathVariable Long matchId,
            @RequestBody TicketDto ticketDto // May contain seat info, etc.
    ) throws MemberNotFoundException, MatchNotFoundException, TicketNotFoundException, TicketAlreadyExistException, MatchGoneException, FacilityNotFoundException, AllTicketPurchasesException {
        var ticketResponse = ticketService.purchaseTicket(memberId, matchId, ticketDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketResponse);
    }


    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<?> handleTicketNotFoundException() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new errorMessageDto("Ticket not found"));
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<?> handleMemberNotFoundException() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new errorMessageDto("Member not found"));
    }

    @ExceptionHandler(MatchNotFoundException.class)
    public ResponseEntity<?> handleMatchNotFoundException() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new errorMessageDto("Match not found"));
    }

    @ExceptionHandler(TicketAlreadyExistException.class)
    public ResponseEntity<?> handleTicketAlreadyExistException() {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new errorMessageDto("Ticket already exists"));
    }

    @ExceptionHandler(MatchGoneException.class)
    public ResponseEntity<?> handleMatchGoneException() {
        return ResponseEntity
                .status(HttpStatus.GONE)
                .body(new errorMessageDto("Match is gone"));
    }

    @ExceptionHandler(FacilityNotFoundException.class)
    public ResponseEntity<?> handleFacilityNotFoundException() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new errorMessageDto("Facility not found"));
    }

    @ExceptionHandler(AllTicketPurchasesException.class)
    public ResponseEntity<?> handleAllTicketPurchasesException() {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new errorMessageDto("All tickets for this match have been purchased"));
    }

}
