package com.yassine.sport_club_projet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OauthResponseDto {
    private OauthProvider provider;
    private String providerUserId;
    private String email;
    private Boolean emailVerified;
    private String givenName;
    private String familyName;
}
