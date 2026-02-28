package com.yassine.sport_club_projet.services;

import com.yassine.sport_club_projet.dto.TicketResponseDto;
import com.yassine.sport_club_projet.entites.*;
import com.yassine.sport_club_projet.exceptions.*;
import com.yassine.sport_club_projet.repositories.*;
import org.antlr.v4.runtime.misc.LogManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test") // This forces the use of application-test.yml
@Transactional // This ensures the database is cleaned after the test
public class TicketServiceIT {

    @Autowired private TicketService ticketService;
    @Autowired private TicketRepository ticketRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired private MatchRepository matchRepository;
    @Autowired private FacilityRepository facilityRepository;
    @Autowired private TeamRepository teamRepository;

    @Test
    @Transactional
    void purchaseTicket_Success_ShouldSaveInRealDb() throws Exception, MatchGoneException, FacilityNotFoundException, AllTicketPurchasesException, MemberNotFoundException, TicketAlreadyExistException {
        // 1. Arrange: Setup the environment in the H2 DB
        Facility facility = Facility.builder()
                .capacity(1)
                .name("Bernabio")
                .location("tanja")
                .build();
        Facility stadium = facilityRepository.save(facility);
        Team teamA = new Team();
        teamA.setName("FC Barcelona");
        teamA = teamRepository.save(teamA); // Make sure to save the team
        // Note: Your code logic requires isExpired to be true to pass
        Match match = matchRepository.save(Match.builder()
                .facility(stadium)
                .team(teamA)
                .opponentName("Atletico Madrid")
                .dateTime(LocalDate.now().plusDays(5).atTime(18, 0))
                .tickets(new HashSet<>())
                .build());

        Member member = new Member();
        User user = new User();
        user.setFirstname("Yassine");
        user.setLastname("khamlichi");
        user.setEmail("yassine@test.com");
        user.setRole(Role.MEMBER);
        user.setPassword("hhhhhhhh");
        member.setUser(user);
        member.setBirthDate(LocalDate.of(1990, 5, 15));
        member = memberRepository.save(member);


        // 2. Act
        TicketResponseDto result = ticketService.purchaseTicket(member.getId(), match.getId());

        // 3. Assert
        assertNotNull(result);
        assertEquals(1, ticketRepository.count()); // Verify row exists in DB

        Ticket savedTicket = ticketRepository.findAll().getFirst();
        assertEquals(member, savedTicket.getMember());
        assertEquals(match, savedTicket.getMatch());
    }

    @Test
    @Transactional
    void purchaseTicket_WhenCapacityFull_ShouldThrowException() {
        // Arrange: Create a stadium with capacity of 1
        Facility smallGym = facilityRepository.save(
                Facility.builder()
                        .capacity(1)
                        .name("Bernabio")
                        .location("tanja")
                        .build()
        );

        Team teamA = new Team();
        teamA.setName("FC Barcelona");
        teamA = teamRepository.save(teamA); // Make sure to save the team

        Match match = matchRepository.save(Match.builder()
                .team(teamA)
                .opponentName("Atletico Madrid")
                .dateTime(LocalDate.now().plusDays(5).atTime(18, 0))
                .tickets(new HashSet<>())
                .facility(smallGym)
                .build());

        // Simulate 1 ticket already sold
        Ticket existingTicket = new Ticket();
        existingTicket.setMatch(match); // Set bidirectional relationship
        match.setTickets(new HashSet<>());
        match.getTickets().add(existingTicket);

        match = matchRepository.save(match);

        Member member = new Member();
        User user = new User();
        user.setFirstname("Yassine");
        user.setLastname("khamlichi");
        user.setEmail("yassine@test.com");
        user.setRole(Role.valueOf("MEMBER"));
        user.setPassword("hhhhhhhh");
        member.setUser(user);
        member.setBirthDate(LocalDate.of(1990, 5, 15));
        member = memberRepository.save(member);
        // Act & Assert
        Match finalMatch = match;
        Member finalMember = member;
        assertThrows(AllTicketPurchasesException.class, () -> {
            ticketService.purchaseTicket(finalMember.getId(), finalMatch.getId());
        });
    }
}