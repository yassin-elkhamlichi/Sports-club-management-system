package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.TicketDto;
import com.yassine.sport_club_projet.dto.TicketRequestDto;
import com.yassine.sport_club_projet.dto.UpdateTicketRequestDto;
import com.yassine.sport_club_projet.entites.Match;
import com.yassine.sport_club_projet.entites.Member;
import com.yassine.sport_club_projet.entites.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface TicketMapper {
    TicketDto toDto(Ticket ticket);

    @Mapping(target = "match", source = "matchId", qualifiedByName = "idToMatch")
    @Mapping(target = "member", source = "memberId", qualifiedByName = "idToMember")
    Ticket toEntity(TicketRequestDto ticketRequestDto);


    Ticket toEntity(TicketDto ticketDto);
    @Mapping(target = "match", source = "matchId", qualifiedByName = "idToMatch")
    @Mapping(target = "member", source = "memberId", qualifiedByName = "idToMember")
    @Mapping(target = "id", ignore = true)
    void update(UpdateTicketRequestDto updateTicketRequestDto, @MappingTarget Ticket ticket);

    @Named("idToMatch")
    default Match idToMatch(Long id) {
        if (id == null) return null;
        Match match = new Match();
        match.setId(id);
        return match;
    }

    @Named("idToMember")
    default Member idToMember(Long id) {
        if (id == null) return null;
        Member member = new Member();
        member.setId(id);
        return member;
    }
}
