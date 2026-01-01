package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.MatchDto;
import com.yassine.sport_club_projet.entites.Match;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , uses = {TicketMapper.class, TeamMapper.class, PlayerMapper.class})
public interface MatchMapper {

    MatchDto toDto(Match match);

}
