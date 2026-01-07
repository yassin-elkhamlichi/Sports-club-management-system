package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.SubscriptionDto;
import com.yassine.sport_club_projet.entites.Subscription;
import com.yassine.sport_club_projet.entites.SubscriptionPlan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    @Mapping(target = "planId", source = "plan.id")
    SubscriptionDto toDto(Subscription subscription);
    @Mapping(target = "plan", source = "planId", qualifiedByName = "IdToPlan")
    Subscription toEntity(SubscriptionDto dto);
    void update(SubscriptionDto dto, @MappingTarget Subscription entity);

    @Named("IdToPlan")
    default SubscriptionPlan idToPlan(Long id) {
        if (id == null) return null;
        SubscriptionPlan plan = new SubscriptionPlan();
        plan.setId(id);
        return plan;
    }
}
