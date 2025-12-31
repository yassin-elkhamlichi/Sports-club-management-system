package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.UpdatePlayerRequestDto;
import com.yassine.sport_club_projet.dto.UpdateUserPlayerRequestDto;
import com.yassine.sport_club_projet.dto.UpdateUserRequestDto;
import com.yassine.sport_club_projet.entites.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    void update(UpdateUserRequestDto updateUserRequestDto , @MappingTarget User user);

    UpdateUserRequestDto toDto(UpdateUserPlayerRequestDto updateUserPlayerRequestDto);

}
