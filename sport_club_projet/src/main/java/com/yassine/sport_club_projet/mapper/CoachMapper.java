package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.CoachResponseDto;
import com.yassine.sport_club_projet.dto.UpdateCoachDto;
import com.yassine.sport_club_projet.dto.UpdateUserCoachRequestDto;
import com.yassine.sport_club_projet.dto.UserCoachRequestDto;
import com.yassine.sport_club_projet.entites.Coach;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring" ,  uses = {TeamMapper.class})
public interface CoachMapper {

    @Mapping(source = "user.id", target = "id")
    CoachResponseDto toDto(Coach coach);

    Coach toEntity(UserCoachRequestDto userCoachRequestDto);

    UpdateCoachDto toDto(UpdateUserCoachRequestDto updateUserCoachRequestDto);

    @Mapping(target="id", ignore=true)
    void update(UpdateCoachDto coachUpdate,@MappingTarget Coach coach);
}
