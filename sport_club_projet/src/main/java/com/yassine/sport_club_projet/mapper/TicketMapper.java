package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.TicketDto;
import com.yassine.sport_club_projet.entites.Ticket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    TicketDto toDto(Ticket ticket);
}
