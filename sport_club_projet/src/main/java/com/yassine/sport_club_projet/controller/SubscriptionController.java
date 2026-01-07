package com.yassine.sport_club_projet.controller;

import com.yassine.sport_club_projet.dto.SubscriptionDto;
import com.yassine.sport_club_projet.exceptions.MemberNotFoundException;
import com.yassine.sport_club_projet.exceptions.SubscriptionNotFound;
import com.yassine.sport_club_projet.exceptions.SubscriptionPlanNotFound;
import com.yassine.sport_club_projet.exceptions.SubscriptionStillActiveException;
import com.yassine.sport_club_projet.services.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @GetMapping
    public ResponseEntity<List<SubscriptionDto>> getAllSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionDto> getSubscription(@PathVariable Long id) {
        SubscriptionDto dto = subscriptionService.getSubscription(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping("{memberId}")
    public ResponseEntity<SubscriptionDto> addSubscription(@RequestBody SubscriptionDto subscriptionDto , @PathVariable Long memberId) throws MemberNotFoundException {
        SubscriptionDto created = subscriptionService.addSubscription(subscriptionDto , memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionDto> updateSubscription(@PathVariable Long id, @RequestBody SubscriptionDto subscriptionDto) {
        SubscriptionDto updated = subscriptionService.updateSubscription(id, subscriptionDto);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/{id}/renew")
    public ResponseEntity<SubscriptionDto> renewSubscription(@PathVariable Long id) throws SubscriptionNotFound {
        SubscriptionDto renewed = subscriptionService.renewSubscription(id);
        return ResponseEntity.ok(renewed);
    }

    @PostMapping("/{idSubs}/changePlan?")
    public ResponseEntity<SubscriptionDto> changeSubscriptionPlan(@PathVariable Long idSubs, @RequestParam Long planId) throws SubscriptionNotFound, SubscriptionPlanNotFound {
        SubscriptionDto renewed = subscriptionService.ChangeSubscriptionPlan(idSubs, planId);
        return ResponseEntity.ok(renewed);
    }

    @PostMapping("/{id}/stop")
    public ResponseEntity<Void> stopSubscription(@PathVariable Long id) throws SubscriptionNotFound {
       subscriptionService.stopSubscription(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long id) throws SubscriptionNotFound, SubscriptionStillActiveException {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.noContent().build();
    }


}
