package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.CoachResponseDto;
import com.yassine.sport_club_projet.dto.TeamDto;
import com.yassine.sport_club_projet.dto.UpdateCoachDto;
import com.yassine.sport_club_projet.dto.UpdateUserCoachRequestDto;
import com.yassine.sport_club_projet.dto.UserCoachRequestDto;
import com.yassine.sport_club_projet.entites.Coach;
import com.yassine.sport_club_projet.entites.Team;
import com.yassine.sport_club_projet.entites.User;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-26T01:10:21+0000",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class CoachMapperImpl implements CoachMapper {

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public CoachResponseDto toDto(Coach coach) {
        if ( coach == null ) {
            return null;
        }

        Long id = null;
        Set<TeamDto> teams = null;
        String specialization = null;
        String certificateLevel = null;

        id = coachUserId( coach );
        teams = teamSetToTeamDtoSet( coach.getTeams() );
        specialization = coach.getSpecialization();
        certificateLevel = coach.getCertificateLevel();

        CoachResponseDto coachResponseDto = new CoachResponseDto( id, specialization, certificateLevel, teams );

        return coachResponseDto;
    }

    @Override
    public Coach toEntity(UserCoachRequestDto userCoachRequestDto) {
        if ( userCoachRequestDto == null ) {
            return null;
        }

        Coach coach = new Coach();

        coach.setSpecialization( userCoachRequestDto.getSpecialization() );
        coach.setCertificateLevel( userCoachRequestDto.getCertificateLevel() );

        return coach;
    }

    @Override
    public UpdateCoachDto toDto(UpdateUserCoachRequestDto updateUserCoachRequestDto) {
        if ( updateUserCoachRequestDto == null ) {
            return null;
        }

        String specialization = null;
        String certificateLevel = null;

        specialization = updateUserCoachRequestDto.getSpecialization();
        certificateLevel = updateUserCoachRequestDto.getCertificateLevel();

        UpdateCoachDto updateCoachDto = new UpdateCoachDto( specialization, certificateLevel );

        return updateCoachDto;
    }

    @Override
    public void update(UpdateCoachDto coachUpdate, Coach coach) {
        if ( coachUpdate == null ) {
            return;
        }

        coach.setSpecialization( coachUpdate.getSpecialization() );
        coach.setCertificateLevel( coachUpdate.getCertificateLevel() );
    }

    private Long coachUserId(Coach coach) {
        User user = coach.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    protected Set<TeamDto> teamSetToTeamDtoSet(Set<Team> set) {
        if ( set == null ) {
            return null;
        }

        Set<TeamDto> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( Team team : set ) {
            set1.add( teamMapper.toDto( team ) );
        }

        return set1;
    }
}
