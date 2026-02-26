package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.SubscriptionDto;
import com.yassine.sport_club_projet.entites.Member;
import com.yassine.sport_club_projet.entites.Subscription;
import com.yassine.sport_club_projet.entites.SubscriptionPlan;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-26T01:10:20+0000",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class SubscriptionMapperImpl implements SubscriptionMapper {

    @Override
    public SubscriptionDto toDto(Subscription subscription) {
        if ( subscription == null ) {
            return null;
        }

        Long planId = null;
        Long memberId = null;
        LocalDate startDate = null;
        LocalDate endDate = null;
        String status = null;

        planId = subscriptionPlanId( subscription );
        memberId = subscriptionMemberId( subscription );
        startDate = subscription.getStartDate();
        endDate = subscription.getEndDate();
        status = subscription.getStatus();

        SubscriptionDto subscriptionDto = new SubscriptionDto( startDate, endDate, status, planId, memberId );

        return subscriptionDto;
    }

    @Override
    public Subscription toEntity(SubscriptionDto dto) {
        if ( dto == null ) {
            return null;
        }

        Subscription subscription = new Subscription();

        subscription.setPlan( idToPlan( dto.getPlanId() ) );
        subscription.setStartDate( dto.getStartDate() );
        subscription.setEndDate( dto.getEndDate() );
        subscription.setStatus( dto.getStatus() );

        return subscription;
    }

    @Override
    public void update(SubscriptionDto dto, Subscription entity) {
        if ( dto == null ) {
            return;
        }

        entity.setStartDate( dto.getStartDate() );
        entity.setEndDate( dto.getEndDate() );
        entity.setStatus( dto.getStatus() );
    }

    private Long subscriptionPlanId(Subscription subscription) {
        SubscriptionPlan plan = subscription.getPlan();
        if ( plan == null ) {
            return null;
        }
        return plan.getId();
    }

    private Long subscriptionMemberId(Subscription subscription) {
        Member member = subscription.getMember();
        if ( member == null ) {
            return null;
        }
        return member.getId();
    }
}
