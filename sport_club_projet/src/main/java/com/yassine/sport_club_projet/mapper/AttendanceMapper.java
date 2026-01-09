package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.AttendanceDto;
import com.yassine.sport_club_projet.entites.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {
    @Mapping(target = "matchId", source = "match.id")
    @Mapping(target = "playerName", source = "player.id")
    AttendanceDto toDto(Attendance attendance);

}
