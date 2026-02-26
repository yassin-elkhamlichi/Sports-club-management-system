package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.MatchDto;
import com.yassine.sport_club_projet.dto.PlayerResponseDto;
import com.yassine.sport_club_projet.dto.TeamDto;
import com.yassine.sport_club_projet.entites.Category;
import com.yassine.sport_club_projet.entites.Coach;
import com.yassine.sport_club_projet.entites.Player;
import com.yassine.sport_club_projet.entites.Team;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-26T01:10:19+0000",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class TeamMapperImpl implements TeamMapper {

    @Autowired
    private PlayerMapper playerMapper;

    @Override
    public TeamDto toDto(Team team) {
        if ( team == null ) {
            return null;
        }

        Long categoryId = null;
        Long coachId = null;
        Set<PlayerResponseDto> players = null;
        Long id = null;
        String name = null;

        categoryId = teamCategoryId( team );
        coachId = teamCoachId( team );
        players = playerSetToPlayerResponseDtoSet( team.getPlayers() );
        id = team.getId();
        name = team.getName();

        Set<MatchDto> matches = null;

        TeamDto teamDto = new TeamDto( id, name, categoryId, coachId, matches, players );

        return teamDto;
    }

    private Long teamCategoryId(Team team) {
        Category category = team.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getId();
    }

    private Long teamCoachId(Team team) {
        Coach coach = team.getCoach();
        if ( coach == null ) {
            return null;
        }
        return coach.getId();
    }

    protected Set<PlayerResponseDto> playerSetToPlayerResponseDtoSet(Set<Player> set) {
        if ( set == null ) {
            return null;
        }

        Set<PlayerResponseDto> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( Player player : set ) {
            set1.add( playerMapper.toDto( player ) );
        }

        return set1;
    }
}
