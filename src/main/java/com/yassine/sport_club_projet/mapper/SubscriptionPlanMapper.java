package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.SubscriptionPlanDto;
import com.yassine.sport_club_projet.entites.SubscriptionPlan;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring")
public interface SubscriptionPlanMapper {

    SubscriptionPlanDto toDto(SubscriptionPlan subscriptionPlan);

}

