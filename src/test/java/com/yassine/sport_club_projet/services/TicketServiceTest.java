package com.yassine.sport_club_projet.services;

import com.yassine.sport_club_projet.dto.*;
import com.yassine.sport_club_projet.entites.*;
import com.yassine.sport_club_projet.exceptions.*;
import com.yassine.sport_club_projet.mapper.TicketMapper;
import com.yassine.sport_club_projet.repositories.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private TicketMapper ticketMapper;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private MatchRepository matchRepository;
    @Mock
    private FacilityRepository faciltyRepository;

    @InjectMocks
    private TicketService ticketService;

    // --- TEST 1: GET TICKET (HAPPY PATH) ---
    @Test
    void getTicket_ExistingId_ReturnsTicketResponseDto() throws TicketNotFoundException {
        Ticket mockTicket = new Ticket();
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(mockTicket));
        when(ticketMapper.toDto(mockTicket)).thenReturn(new TicketResponseDto());

        TicketResponseDto result = ticketService.getTicket(1L);

        assertNotNull(result);
        verify(ticketRepository).findById(1L);
    }

    // --- TEST 2: PURCHASE TICKET (COMPLEX LOGIC) ---
    @Test
    void purchaseTicket_CapacityFull_ThrowsException() {
        // Arrange
        Long memberId = 1L;
        Long matchId = 10L;
        TicketResponseDto dto = new TicketResponseDto();

        Member mockMember = new Member();

        // Setup Match with a Facility
        Facility mockFacility = new Facility();
        mockFacility.setId(5L);
        mockFacility.setCapacity(50); // Max capacity is 50

        Match mockMatch = spy(new Match());
        mockMatch.setDateTime(java.time.LocalDateTime.now().plusDays(1)); // Future date
        mockMatch.setFacility(mockFacility);

        // Mock match.isExpired() to return true so it passes the first check
        // Note: Your code says if(!match.isExpired()) throw MatchGoneException
        when(mockMatch.isExpired()).thenReturn(false);

        // Simulate that the match already has 50 tickets (Capacity Full)
        Set<Ticket> fullTickets = new HashSet<>();
        for (int i = 0; i < 50; i++)
            fullTickets.add(new Ticket());
        mockMatch.setTickets(fullTickets);

        when(memberRepository.findById(memberId)).thenReturn(Optional.of(mockMember));
        // TicketService.purchaseTicket calls findByIdWithFacility, not findById
        when(matchRepository.findByIdWithFacility(matchId)).thenReturn(Optional.of(mockMatch));
        when(faciltyRepository.findById(5L)).thenReturn(Optional.of(mockFacility));

        // Act & Assert
        assertThrows(AllTicketPurchasesException.class, () -> {
            ticketService.purchaseTicket(memberId, matchId);
        });
    }

    // --- TEST 3: DELETE TICKET (NOT FOUND) ---
    @Test
    void deleteTicket_NotFound_ThrowsException() {
        when(ticketRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(TicketNotFoundException.class, () -> ticketService.deleteTicket(99L));

        // Ensure delete was NEVER called because the exception was thrown first
        verify(ticketRepository, never()).delete(any());
    }
}