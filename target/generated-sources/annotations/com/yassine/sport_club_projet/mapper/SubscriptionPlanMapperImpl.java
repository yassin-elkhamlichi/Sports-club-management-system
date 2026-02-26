package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.SubscriptionPlanDto;
import com.yassine.sport_club_projet.entites.SubscriptionPlan;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-26T01:10:21+0000",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class SubscriptionPlanMapperImpl implements SubscriptionPlanMapper {

    @Override
    public SubscriptionPlanDto toDto(SubscriptionPlan subscriptionPlan) {
        if ( subscriptionPlan == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        BigDecimal price = null;
        Integer durationMonths = null;
        String description = null;

        id = subscriptionPlan.getId();
        name = subscriptionPlan.getName();
        price = subscriptionPlan.getPrice();
        durationMonths = subscriptionPlan.getDurationMonths();
        description = subscriptionPlan.getDescription();

        SubscriptionPlanDto subscriptionPlanDto = new SubscriptionPlanDto( id, name, price, durationMonths, description );

        return subscriptionPlanDto;
    }
}
