package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.TicketResponseDto;
import com.yassine.sport_club_projet.dto.UpdateTicketRequestDto;
import com.yassine.sport_club_projet.entites.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring", uses = {MatchMapper.class})
public interface TicketMapper {

    @Mapping(target = "memberId", source = "member.id")
    @Mapping(target = "matchDto", source = "match")
    TicketResponseDto toDto(Ticket ticket);

    @Mapping(target = "member.id", source = "memberId")
    Ticket toDto(TicketResponseDto ticketResponseDto);



    @Mapping(target = "match.id", source = "matchId")
    @Mapping(target = "member.id", source = "memberId")
    @Mapping(target = "id", ignore = true)
    void update(UpdateTicketRequestDto updateTicketRequestDto, @MappingTarget Ticket ticket);


    // Ticket toEntity(TicketRequestDto ticketRequestDto);
}
