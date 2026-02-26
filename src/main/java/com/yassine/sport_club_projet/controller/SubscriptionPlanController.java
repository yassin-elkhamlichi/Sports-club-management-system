package com.yassine.sport_club_projet.controller;

import com.yassine.sport_club_projet.dto.SubscriptionPlanDto;
import com.yassine.sport_club_projet.services.SubscriptionPlanService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/subscriptionPlans")
public class SubscriptionPlanController {
    private final SubscriptionPlanService subscriptionPlanService;

    @GetMapping
    public ResponseEntity<List<SubscriptionPlanDto>> getAllSubscriptionPlans() {
        return ResponseEntity.ok(subscriptionPlanService.getAllSubscriptionPlans());
    }


}
