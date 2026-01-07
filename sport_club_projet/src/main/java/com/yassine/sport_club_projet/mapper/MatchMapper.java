package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.CreateMatchRequestDto;
import com.yassine.sport_club_projet.dto.MatchDto;
import com.yassine.sport_club_projet.dto.UpdateMatchRequestDto;
import com.yassine.sport_club_projet.entites.Match;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MatchMapper {
    @Mapping( target = "remainingTicket" , expression = "java(match. remainingTicket())")
    @Mapping( target = "facilityName" , source = "facility.name")
    @Mapping( target = "teamName" , source = "team.name")
    MatchDto toDto(Match match);

    @Mapping(target = "attendances", ignore = true)
    Match toEntity(CreateMatchRequestDto createMatchRequestDto);

    @Mapping(target = "id", ignore = true)
    void update(UpdateMatchRequestDto dto, @MappingTarget Match entity);
}