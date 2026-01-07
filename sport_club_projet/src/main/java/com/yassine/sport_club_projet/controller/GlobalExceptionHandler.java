package com.yassine.sport_club_projet.controller;

import com.yassine.sport_club_projet.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalExceptionHandler {

    // Keep YOUR exact messages below 👇

    @ExceptionHandler(CoachNotFoundException.class)
    public ResponseEntity<?> handleCoachException() {

        return ResponseEntity.status(HttpStatus.NOT_FOUND) // Fixed status
                .body("Coach not found");
    }

    @ExceptionHandler(TeamNotFoundException.class)
    public ResponseEntity<?> handleTeamNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND) // Fixed status
                .body("Team not found");
    }

    @ExceptionHandler(CoachNotManageThisTeamException.class)
    public ResponseEntity<?> handleCTNotFoundException() {

        return ResponseEntity.status(HttpStatus.FORBIDDEN) // More accurate than BAD_REQUEST
                .body("this coach not responsable for this team");
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<?> handleMemberException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND) // Fixed status
                .body("Member not found");
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<?> handleUserExistException() {
        return ResponseEntity.status(HttpStatus.CONFLICT) // Fixed status
                .body("User already exist");
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<?> handlePlayerException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND) // Fixed status
                .body("Player not found");
    }

    @ExceptionHandler(SubscriptionNotFound.class)
    public ResponseEntity<?> handleSubscriptionNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Subscription not found");
    }

    @ExceptionHandler(SubscriptionStillActiveException.class)
    public ResponseEntity<?> handleSubscriptionStillActive() {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Subscription is still active");
    }

    @ExceptionHandler(SubscriptionPlanNotFound.class)
    public ResponseEntity<?> handleSubscriptionPlanNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Subscription plan not found");
    }

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<?> handleTicketNotFoundException() {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Ticket not found");
    }

    @ExceptionHandler(TicketAlreadyExistException.class)
    public ResponseEntity<?> handleTicketAlreadyExistException() {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Ticket already exists");
    }

    @ExceptionHandler(MatchGoneException.class)
    public ResponseEntity<?> handleMatchGoneException() {
        return ResponseEntity.status(HttpStatus.GONE)
                .body("Match is gone");
    }

    @ExceptionHandler(FacilityNotFoundException.class)
    public ResponseEntity<?> handleFacilityNotFoundException() {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Facility not found");
    }

    @ExceptionHandler(AllTicketPurchasesException.class)
    public ResponseEntity<?> handleAllTicketPurchasesException() {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("All tickets for this match have been purchased");
    }

}