package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.OauthResponseDto;
import com.yassine.sport_club_projet.entites.UserIdentity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;


@Mapper(componentModel = "spring")
public interface UserIdentityMapper {

    @Mapping(target = "user", ignore = true)
    UserIdentity toEntity(OauthResponseDto oauthResponseDto);

    @Mapping(target = "provider", ignore = true )
    @Mapping(target = "providerUserId", source = "subject")
    OauthResponseDto toDto(OidcUser oidcUser);

}
