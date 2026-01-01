package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.TeamDto;
import com.yassine.sport_club_projet.entites.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PlayerMapper.class})
public interface TeamMapper {

    @Mapping(target = "categoryId" , source = "category.id")
    @Mapping(target = "coachId" , source = "coach.id")
    @Mapping(target = "matches", ignore = true)
    TeamDto toDto(Team team);

}
