package com.yassine.sport_club_projet.services;

import com.yassine.sport_club_projet.dto.SubscriptionPlanDto;
import com.yassine.sport_club_projet.mapper.SubscriptionPlanMapper;
import com.yassine.sport_club_projet.repositories.SubscriptionPlanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SubscriptionPlanService {
    private final SubscriptionPlanRepository subscriptionPlanRepository;
    private final SubscriptionPlanMapper subscriptionPlanMapper;

    public List<SubscriptionPlanDto> getAllSubscriptionPlans() {
        return subscriptionPlanRepository.findAll().stream()
                .map(subscriptionPlanMapper::toDto)
                .toList();
    }
}
