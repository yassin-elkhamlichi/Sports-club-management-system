package com.yassine.sport_club_projet.services;

import com.yassine.sport_club_projet.dto.SubscriptionDto;
import com.yassine.sport_club_projet.dto.SubscriptionReNowDto;
import com.yassine.sport_club_projet.entites.Member;
import com.yassine.sport_club_projet.entites.Subscription;
import com.yassine.sport_club_projet.entites.SubscriptionPlan;
import com.yassine.sport_club_projet.exceptions.MemberNotFoundException;
import com.yassine.sport_club_projet.exceptions.SubscriptionNotFound;
import com.yassine.sport_club_projet.exceptions.SubscriptionPlanNotFound;
import com.yassine.sport_club_projet.mapper.SubscriptionMapper;
import com.yassine.sport_club_projet.repositories.MemberRepository;
import com.yassine.sport_club_projet.repositories.SubscriptionPlanRepository;
import com.yassine.sport_club_projet.repositories.SubscriptionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;
    @Mock
    private SubscriptionMapper subscriptionMapper;
    @Mock
    private SubscriptionPlanRepository planRepository;
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private SubscriptionService subscriptionService;

    // --- TEST 1: RENEW SUBSCRIPTION (Logic Test) ---
    @Test
    void renewSubscription_Success() throws SubscriptionNotFound {
        // Arrange
        Long subsId = 1L;
        LocalDate originalEndDate = LocalDate.of(2025, 1, 1);

        Subscription mockSubs = new Subscription();
        mockSubs.setId(subsId);
        mockSubs.setEndDate(originalEndDate);
        mockSubs.setStatus("INACTIVE");

        SubscriptionReNowDto renewDto = new SubscriptionReNowDto(3L); // Renew for 3 months

        when(subscriptionRepository.findById(subsId)).thenReturn(Optional.of(mockSubs));
        when(subscriptionRepository.save(any(Subscription.class))).thenAnswer(i -> i.getArguments()[0]);
        when(subscriptionMapper.toDto(any())).thenReturn(new SubscriptionDto());

        // Act
        subscriptionService.renewSubscription(subsId, renewDto);

        // Assert
        // Check if the date was increased by 3 months (Jan -> April)
        assertEquals(LocalDate.of(2025, 4, 1), mockSubs.getEndDate());
        // Note: renewSubscription() does not change the status, only the end date.
        // Status remains as it was ("INACTIVE").
        verify(subscriptionRepository).save(mockSubs);
    }

    // --- TEST 2: CHANGE PLAN (Relationship Test) ---
    @Test
    void changeSubscriptionPlan_Success() throws SubscriptionNotFound, SubscriptionPlanNotFound {
        // Arrange
        Long subsId = 1L;
        Long planId = 10L;

        Subscription mockSubs = new Subscription();
        SubscriptionPlan newPlan = new SubscriptionPlan();
        newPlan.setId(planId);

        when(subscriptionRepository.findById(subsId)).thenReturn(Optional.of(mockSubs));
        when(planRepository.findById(planId)).thenReturn(Optional.of(newPlan));
        when(subscriptionMapper.toDto(any())).thenReturn(new SubscriptionDto());

        // Act
        subscriptionService.ChangeSubscriptionPlan(subsId, planId);

        // Assert
        assertEquals(newPlan, mockSubs.getPlan());
        verify(subscriptionRepository).save(mockSubs);
    }

    // --- TEST 3: STOP SUBSCRIPTION (Update Test) ---
    @Test
    void stopSubscription_Success() throws SubscriptionNotFound {
        // Arrange
        Subscription mockSubs = new Subscription();
        mockSubs.setStatus("ACTIVE");

        when(subscriptionRepository.findById(1L)).thenReturn(Optional.of(mockSubs));
        when(subscriptionRepository.save(any(Subscription.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        subscriptionService.stopSubscription(1L);

        // Assert
        assertEquals("inactive", mockSubs.getStatus()); // service uses lowercase "inactive"
        verify(subscriptionRepository).save(mockSubs);
    }

    // --- TEST 4: ADD SUBSCRIPTION (Error Test) ---
    @Test
    void addSubscription_MemberNotFound_ThrowsException() {
        // Arrange
        when(memberRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(subscriptionMapper.toEntity(any())).thenReturn(new Subscription());

        // Act & Assert
        assertThrows(MemberNotFoundException.class, () -> {
            subscriptionService.addSubscription(new SubscriptionDto(), 99L);
        });
    }
}