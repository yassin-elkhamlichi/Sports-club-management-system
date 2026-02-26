package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.AttendanceDto;
import com.yassine.sport_club_projet.entites.Attendance;
import com.yassine.sport_club_projet.entites.Match;
import com.yassine.sport_club_projet.entites.Player;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-26T01:10:20+0000",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class AttendanceMapperImpl implements AttendanceMapper {

    @Override
    public AttendanceDto toDto(Attendance attendance) {
        if ( attendance == null ) {
            return null;
        }

        Long matchId = null;
        Long playerName = null;
        Long id = null;
        Boolean isPresent = null;
        LocalDateTime checkInTime = null;

        matchId = attendanceMatchId( attendance );
        playerName = attendancePlayerId( attendance );
        id = attendance.getId();
        isPresent = attendance.getIsPresent();
        checkInTime = attendance.getCheckInTime();

        AttendanceDto attendanceDto = new AttendanceDto( id, isPresent, checkInTime, matchId, playerName );

        return attendanceDto;
    }

    private Long attendanceMatchId(Attendance attendance) {
        Match match = attendance.getMatch();
        if ( match == null ) {
            return null;
        }
        return match.getId();
    }

    private Long attendancePlayerId(Attendance attendance) {
        Player player = attendance.getPlayer();
        if ( player == null ) {
            return null;
        }
        return player.getId();
    }
}
