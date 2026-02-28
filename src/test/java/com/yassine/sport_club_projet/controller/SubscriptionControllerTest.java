package com.yassine.sport_club_projet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yassine.sport_club_projet.dto.SubscriptionDto;
import com.yassine.sport_club_projet.dto.SubscriptionReNowDto;
import com.yassine.sport_club_projet.exceptions.MemberNotFoundException;
import com.yassine.sport_club_projet.exceptions.SubscriptionNotFound;
import com.yassine.sport_club_projet.filter.ValidationFilter;
import com.yassine.sport_club_projet.services.JwtService;
import com.yassine.sport_club_projet.services.SubscriptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = SubscriptionController.class)
@AutoConfigureMockMvc(addFilters = false) // Skip security filters for this test
class SubscriptionControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockitoBean
        private SubscriptionService subscriptionService;

        // Required by SecurityConfig → ValidationFilter → JwtService
        @MockitoBean
        private JwtService jwtService;

        // Required so SecurityConfig can inject ValidationFilter as a bean
        @MockitoBean
        private ValidationFilter validationFilter;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        void addSubscription_EndPoint_ReturnsCreatedStatus() throws Exception, MemberNotFoundException {
                // Arrange
                Long memberId = 1L;
                SubscriptionDto requestDto = new SubscriptionDto();
                requestDto.setStartDate(LocalDate.now());

                SubscriptionDto responseDto = new SubscriptionDto();

                when(subscriptionService.addSubscription(any(SubscriptionDto.class), eq(memberId)))
                                .thenReturn(responseDto);

                // Act & Assert
                mockMvc.perform(post("/subscriptions/{memberId}", memberId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestDto)))
                                .andExpect(status().is(201)); // 201 CREATED
        }

        @Test
        void renewSubscription_EndPoint_UpdatesSuccessfully() throws Exception, SubscriptionNotFound {
                // Arrange
                Long subId = 10L;
                SubscriptionReNowDto renewDto = new SubscriptionReNowDto(3L);

                SubscriptionDto responseDto = new SubscriptionDto();
                responseDto.setStatus("ACTIVE");

                when(subscriptionService.renewSubscription(eq(subId), any(SubscriptionReNowDto.class)))
                                .thenReturn(responseDto);

                // Act & Assert
                mockMvc.perform(post("/subscriptions/{id}/renew", subId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(renewDto)))
                                .andExpect(status().is(200))
                                .andExpect(jsonPath("$.status").value("ACTIVE"));
        }
}