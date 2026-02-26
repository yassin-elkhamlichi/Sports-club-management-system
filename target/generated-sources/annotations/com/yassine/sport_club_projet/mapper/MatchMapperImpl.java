package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.CreateMatchRequestDto;
import com.yassine.sport_club_projet.dto.MatchDto;
import com.yassine.sport_club_projet.dto.UpdateMatchRequestDto;
import com.yassine.sport_club_projet.entites.Facility;
import com.yassine.sport_club_projet.entites.Match;
import com.yassine.sport_club_projet.entites.Team;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-26T01:10:22+0000",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class MatchMapperImpl implements MatchMapper {

    @Override
    public MatchDto toDto(Match match) {
        if ( match == null ) {
            return null;
        }

        MatchDto matchDto = new MatchDto();

        matchDto.setFacilityName( matchFacilityName( match ) );
        matchDto.setTeamName( matchTeamName( match ) );
        matchDto.setId( match.getId() );
        matchDto.setDateTime( match.getDateTime() );
        matchDto.setOpponentName( match.getOpponentName() );
        matchDto.setScoreHome( match.getScoreHome() );
        matchDto.setScoreAway( match.getScoreAway() );
        matchDto.setResult( match.getResult() );
        matchDto.setTicketPrice( match.getTicketPrice() );

        matchDto.setRemainingTicket( match. remainingTicket() );

        return matchDto;
    }

    @Override
    public Match toEntity(CreateMatchRequestDto createMatchRequestDto) {
        if ( createMatchRequestDto == null ) {
            return null;
        }

        Match match = new Match();

        match.setFacility( createMatchRequestDtoToFacility( createMatchRequestDto ) );
        match.setDateTime( createMatchRequestDto.getDateTime() );
        match.setTicketPrice( createMatchRequestDto.getTicketPrice() );
        match.setOpponentName( createMatchRequestDto.getOpponentName() );

        return match;
    }

    @Override
    public void update(UpdateMatchRequestDto dto, Match entity) {
        if ( dto == null ) {
            return;
        }

        entity.setNewDate( dto.getNewDate() );
    }

    private String matchFacilityName(Match match) {
        Facility facility = match.getFacility();
        if ( facility == null ) {
            return null;
        }
        return facility.getName();
    }

    private String matchTeamName(Match match) {
        Team team = match.getTeam();
        if ( team == null ) {
            return null;
        }
        return team.getName();
    }

    protected Facility createMatchRequestDtoToFacility(CreateMatchRequestDto createMatchRequestDto) {
        if ( createMatchRequestDto == null ) {
            return null;
        }

        Facility facility = new Facility();

        facility.setId( createMatchRequestDto.getFacilityId() );

        return facility;
    }
}
