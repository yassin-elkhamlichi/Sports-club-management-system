package com.yassine.sport_club_projet.controller;

import com.yassine.sport_club_projet.dto.TicketResponseDto;
import com.yassine.sport_club_projet.dto.UpdateTicketRequestDto;
import com.yassine.sport_club_projet.exceptions.*;
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
    public ResponseEntity<List<TicketResponseDto>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDto> getTicket(
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
    public ResponseEntity<TicketResponseDto> updateTicket(
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
    public ResponseEntity<TicketResponseDto> purchaseTicket(
            @PathVariable Long memberId,
            @PathVariable Long matchId

    ) throws MemberNotFoundException, MatchNotFoundException, TicketNotFoundException, TicketAlreadyExistException, MatchGoneException, FacilityNotFoundException, AllTicketPurchasesException {
        TicketResponseDto ticketResponse = ticketService.purchaseTicket(memberId, matchId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketResponse);
    }

}
