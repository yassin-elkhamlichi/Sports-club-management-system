package com.yassine.sport_club_projet.services;

import com.yassine.sport_club_projet.dto.TicketResponseDto;
import com.yassine.sport_club_projet.dto.UpdateTicketRequestDto;
import com.yassine.sport_club_projet.entites.Ticket;
import com.yassine.sport_club_projet.exceptions.*;
import com.yassine.sport_club_projet.mapper.TicketMapper;

import com.yassine.sport_club_projet.repositories.FacilityRepository;
import com.yassine.sport_club_projet.repositories.MatchRepository;
import com.yassine.sport_club_projet.repositories.MemberRepository;
import com.yassine.sport_club_projet.repositories.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final MemberRepository memberRepository;
    private final MatchRepository matchRepository;
    private final FacilityRepository facilityRepository;

    public List<TicketResponseDto> getAllTickets() {
        return ticketRepository.findAll().stream().map(ticketMapper::toDto).toList();
    }

    public TicketResponseDto getTicket(Long id) throws TicketNotFoundException {
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        if(ticket == null) throw new TicketNotFoundException();
        return ticketMapper.toDto(ticket);
    }


//    public TicketResponseDto addTicket(TicketRequestDto ticketRequestDto) {
//        var ticket = ticketMapper.toEntity(ticketRequestDto);
//        ticketRepository.save(ticket);
//        return ticketMapper.toDto(ticket);
//    }

    public TicketResponseDto updateTicket(Long id, UpdateTicketRequestDto updateTicketRequestDto) throws TicketNotFoundException {
        var ticket = ticketRepository.findById(id).orElse(null);
        if(ticket == null) throw new TicketNotFoundException();
        ticketMapper.update(updateTicketRequestDto , ticket);
        ticketRepository.save(ticket);
        return ticketMapper.toDto(ticket);
    }

    public void deleteTicket(Long id) throws TicketNotFoundException {
        var ticket = ticketRepository.findById(id).orElse(null);
        if(ticket == null) throw new TicketNotFoundException();
        ticketRepository.delete(ticket);
    }

    public TicketResponseDto purchaseTicket(Long memberId, Long matchId) throws  MemberNotFoundException, MatchNotFoundException, TicketAlreadyExistException, MatchGoneException, FacilityNotFoundException, AllTicketPurchasesException {

        var member = memberRepository.findById(memberId).orElse(null);
        if(member == null) throw new MemberNotFoundException();

        var match = matchRepository.findByIdWithFacility(matchId).orElse(null);
        if(match == null) {
            throw new MatchNotFoundException();
        }


        if(match.isExpired())
            throw new MatchGoneException();

        var facility = facilityRepository.findById(match.getFacility().getId()).orElse(null);
        if(facility == null) throw new FacilityNotFoundException();

        Integer capacity = facility.getCapacity();
        if (capacity == null) {
            throw new IllegalStateException("Facility capacity is not set for facility with id: " + facility.getId());
        }

        if(capacity <= match.getTickets().size() )
            throw new AllTicketPurchasesException();

        Ticket ticket = new Ticket();


        ticket.setPrice(match.getTicketPrice());
        ticket.setMatch(match);
        ticket.setMember(member);
        ticketRepository.save(ticket);
        return ticketMapper.toDto(ticket);
    }
}
