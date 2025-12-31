package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.*;
import com.yassine.sport_club_projet.entites.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    @Mapping(source = "user.id", target = "user")
    @Mapping(source = "team.id", target = "teamId")
    PlayerResponseDto toDto(Player player);
    @Mapping(source = "teamId", target = "teamId")
    UpdatePlayerRequestDto toDto(UpdateUserPlayerRequestDto updateUserPlayerRequestDto);

    @Mapping(target = "team.id" , source = "teamId")
    Player toEntity(UserPlayerRequestDto userPlayerRequestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void update(UpdatePlayerRequestDto updateUserRequestDto , @MappingTarget Player player);
}
