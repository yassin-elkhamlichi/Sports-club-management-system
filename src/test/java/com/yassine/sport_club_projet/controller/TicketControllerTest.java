package com.yassine.sport_club_projet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yassine.sport_club_projet.dto.MatchDto;
import com.yassine.sport_club_projet.dto.TicketResponseDto;
import com.yassine.sport_club_projet.exceptions.*;
import com.yassine.sport_club_projet.services.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TicketController.class) // Only load the TicketController
@AutoConfigureMockMvc(addFilters = false) // Disable Spring Security filters for this specific test
class TicketControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockitoBean
        private TicketService ticketService; // Mock the service layer

        @MockitoBean
        private com.yassine.sport_club_projet.services.JwtService jwtService; // Mock JwtService for ValidationFilter

        @Autowired
        private ObjectMapper objectMapper; // Converts Java objects to JSON

        @Test
        void purchaseTicket_EndPoint_Returns201OK() throws Exception, MatchGoneException, FacilityNotFoundException,
                        AllTicketPurchasesException, MemberNotFoundException, TicketAlreadyExistException {
                // Arrange
                Long memberId = 1L;
                Long matchId = 6L;
                MatchDto matchDto = new MatchDto();
                matchDto.setId(matchId);

                TicketResponseDto responseDto = new TicketResponseDto();
                responseDto.setId(100L); // Simulate a saved ticket ID
                responseDto.setPrice(BigDecimal.valueOf(50.0));
                responseDto.setMemberId(memberId);
                responseDto.setMatchDto(matchDto);

                // Mock the service behavior
                when(ticketService.purchaseTicket(eq(memberId), eq(matchId)))
                                .thenReturn(responseDto);

                // Act & Assert
                mockMvc.perform(post("/tickets/purchase/member/{memberId}/match/{matchId}", memberId, matchId)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.id").value(100L));
        }
}