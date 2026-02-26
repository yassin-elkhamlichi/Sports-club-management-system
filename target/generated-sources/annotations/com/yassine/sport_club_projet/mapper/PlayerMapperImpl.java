package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.PlayerResponseDto;
import com.yassine.sport_club_projet.dto.UpdatePlayerRequestDto;
import com.yassine.sport_club_projet.dto.UpdateUserPlayerRequestDto;
import com.yassine.sport_club_projet.dto.UserPlayerRequestDto;
import com.yassine.sport_club_projet.entites.Player;
import com.yassine.sport_club_projet.entites.Team;
import com.yassine.sport_club_projet.entites.User;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-26T01:10:20+0000",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class PlayerMapperImpl implements PlayerMapper {

    @Override
    public PlayerResponseDto toDto(Player player) {
        if ( player == null ) {
            return null;
        }

        PlayerResponseDto playerResponseDto = new PlayerResponseDto();

        playerResponseDto.setUser( playerUserId( player ) );
        playerResponseDto.setTeamId( playerTeamId( player ) );
        playerResponseDto.setJerseyNumber( player.getJerseyNumber() );
        playerResponseDto.setBirthDate( player.getBirthDate() );
        playerResponseDto.setPosition( player.getPosition() );
        playerResponseDto.setMedicalCertificate( player.getMedicalCertificate() );
        playerResponseDto.setHeight( player.getHeight() );
        playerResponseDto.setWeight( player.getWeight() );

        return playerResponseDto;
    }

    @Override
    public UpdatePlayerRequestDto toDto(UpdateUserPlayerRequestDto updateUserPlayerRequestDto) {
        if ( updateUserPlayerRequestDto == null ) {
            return null;
        }

        Long teamId = null;
        LocalDate birthDate = null;
        Integer jerseyNumber = null;
        String position = null;
        String medicalCertificate = null;
        Double height = null;
        Double weight = null;

        teamId = updateUserPlayerRequestDto.getTeamId();
        birthDate = updateUserPlayerRequestDto.getBirthDate();
        jerseyNumber = updateUserPlayerRequestDto.getJerseyNumber();
        position = updateUserPlayerRequestDto.getPosition();
        medicalCertificate = updateUserPlayerRequestDto.getMedicalCertificate();
        height = updateUserPlayerRequestDto.getHeight();
        weight = updateUserPlayerRequestDto.getWeight();

        UpdatePlayerRequestDto updatePlayerRequestDto = new UpdatePlayerRequestDto( birthDate, jerseyNumber, position, medicalCertificate, height, weight, teamId );

        return updatePlayerRequestDto;
    }

    @Override
    public Player toEntity(UserPlayerRequestDto userPlayerRequestDto) {
        if ( userPlayerRequestDto == null ) {
            return null;
        }

        Player player = new Player();

        player.setJerseyNumber( userPlayerRequestDto.getJerseyNumber() );
        player.setBirthDate( userPlayerRequestDto.getBirthDate() );
        player.setPosition( userPlayerRequestDto.getPosition() );
        player.setMedicalCertificate( userPlayerRequestDto.getMedicalCertificate() );
        player.setHeight( userPlayerRequestDto.getHeight() );
        player.setWeight( userPlayerRequestDto.getWeight() );

        return player;
    }

    @Override
    public void update(UpdatePlayerRequestDto updateUserRequestDto, Player player) {
        if ( updateUserRequestDto == null ) {
            return;
        }

        player.setJerseyNumber( updateUserRequestDto.getJerseyNumber() );
        player.setBirthDate( updateUserRequestDto.getBirthDate() );
        player.setPosition( updateUserRequestDto.getPosition() );
        player.setMedicalCertificate( updateUserRequestDto.getMedicalCertificate() );
        player.setHeight( updateUserRequestDto.getHeight() );
        player.setWeight( updateUserRequestDto.getWeight() );
    }

    private Long playerUserId(Player player) {
        User user = player.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    private Long playerTeamId(Player player) {
        Team team = player.getTeam();
        if ( team == null ) {
            return null;
        }
        return team.getId();
    }
}
