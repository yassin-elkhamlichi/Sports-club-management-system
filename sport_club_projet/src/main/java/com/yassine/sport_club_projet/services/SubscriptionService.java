package com.yassine.sport_club_projet.services;

import com.yassine.sport_club_projet.dto.SubscriptionDto;
import com.yassine.sport_club_projet.entites.Subscription;
import com.yassine.sport_club_projet.exceptions.MemberNotFoundException;
import com.yassine.sport_club_projet.exceptions.SubscriptionNotFound;
import com.yassine.sport_club_projet.exceptions.SubscriptionPlanNotFound;
import com.yassine.sport_club_projet.exceptions.SubscriptionStillActiveException;
import com.yassine.sport_club_projet.mapper.SubscriptionMapper;
import com.yassine.sport_club_projet.repository.MemberRepository;
import com.yassine.sport_club_projet.repository.SubscriptionPlanRepository;
import com.yassine.sport_club_projet.repository.SubscriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final SubscriptionPlanRepository planRepository;
    private final MemberRepository memberRepository;

    public List<SubscriptionDto> getAllSubscriptions() {
        return subscriptionRepository.findAll().stream().map(subscriptionMapper::toDto).toList();
    }

    public SubscriptionDto getSubscription(Long id) {
        return subscriptionRepository.findById(id).map(subscriptionMapper::toDto).orElse(null);
    }

    public SubscriptionDto addSubscription(SubscriptionDto subscriptionDto , long memberId) throws MemberNotFoundException {
        Subscription subscription = subscriptionMapper.toEntity(subscriptionDto);
        var member = memberRepository.findById(memberId).orElse(null);
        if(member == null)
            throw new MemberNotFoundException();
        subscription.setMember(member);
        subscriptionRepository.save(subscription);
        return subscriptionMapper.toDto(subscription);
    }

    public SubscriptionDto updateSubscription(Long id, SubscriptionDto subscriptionDto) {
        return subscriptionRepository.findById(id).map(existing -> {
            subscriptionMapper.update(subscriptionDto, existing);
            subscriptionRepository.save(existing);
            return subscriptionMapper.toDto(existing);
        }).orElse(null);
    }


    public void stopSubscription(Long id) throws SubscriptionNotFound {
        Subscription subs =  subscriptionRepository.findById(id).orElse(null);
       if(subs == null)
           throw new SubscriptionNotFound();

       subs.setStatus("inactive");
       subscriptionRepository.save(subs);
    }

    public SubscriptionDto renewSubscription(Long id) throws SubscriptionNotFound {
        var subs = subscriptionRepository.findById(id).orElse(null);
        if(subs == null){
            throw new SubscriptionNotFound();
        }
        subs.setEndDate(subs.getEndDate().plusMonths(1));
        subscriptionRepository.save(subs);
        return subscriptionMapper.toDto(subs);
    }


    public SubscriptionDto ChangeSubscriptionPlan(Long idSubs , Long planId ) throws SubscriptionNotFound, SubscriptionPlanNotFound {
        var subs = subscriptionRepository.findById(idSubs).orElse(null);
        if(subs == null){
            throw new SubscriptionNotFound();
        }
        var plan = planRepository.findById(planId).orElse(null);
        if(plan == null){
            throw new SubscriptionPlanNotFound();
        }
        subs.setPlan(plan);
        subscriptionRepository.save(subs);
        return subscriptionMapper.toDto(subs);
    }

    public void deleteSubscription(Long id) throws SubscriptionNotFound {
        var subs = subscriptionRepository.findById(id).orElse(null);
        if(subs == null){
            throw new SubscriptionNotFound();
        }
        subscriptionRepository.deleteById(id);
    }
}

