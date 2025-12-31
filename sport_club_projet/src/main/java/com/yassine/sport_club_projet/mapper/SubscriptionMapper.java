package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.SubscriptionDto;
import com.yassine.sport_club_projet.entites.Subscription;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    SubscriptionDto toDto(Subscription subscription);
}
