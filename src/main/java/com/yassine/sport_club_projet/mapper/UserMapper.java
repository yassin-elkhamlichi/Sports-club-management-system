package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.*;
import com.yassine.sport_club_projet.entites.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    void update(UpdateUserRequestDto updateUserRequestDto , @MappingTarget User user);

    UpdateUserRequestDto toDto(UpdateUserPlayerRequestDto updateUserPlayerRequestDto);

    User toEntity(UserCoachRequestDto userCoachRequestDto);

    UpdateUserRequestDto toDto(UpdateUserCoachRequestDto updateUserCoachRequestDto);

    User toEntity(UserSignUpDto userSignUpDto);
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "userIdentity" , ignore = true)
    @Mapping(target = "lastname", source = "familyName")
    @Mapping(target = "firstname", source = "givenName")
    User toEntity(OauthResponseDto oauthResponseDto);
}
