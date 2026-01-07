package com.yassine.sport_club_projet.services;

import com.yassine.sport_club_projet.dto.TicketDto;
import com.yassine.sport_club_projet.dto.TicketRequestDto;
import com.yassine.sport_club_projet.dto.UpdateTicketRequestDto;
import com.yassine.sport_club_projet.entites.Facility;
import com.yassine.sport_club_projet.entites.Match;
import com.yassine.sport_club_projet.entites.Ticket;
import com.yassine.sport_club_projet.exceptions.*;
import com.yassine.sport_club_projet.mapper.TicketMapper;
import com.yassine.sport_club_projet.repository.FacilityRepository;
import com.yassine.sport_club_projet.repository.MatchRepository;
import com.yassine.sport_club_projet.repository.MemberRepository;
import com.yassine.sport_club_projet.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final MemberRepository memberRepository;
    private final MatchRepository matchRepository;
    private final FacilityRepository faciltyRepository;

    public List<TicketDto> getAllTickets() {
        return ticketRepository.findAll().stream().map(ticketMapper::toDto).toList();
    }

    public TicketDto getTicket(Long id) throws TicketNotFoundException {
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        if(ticket == null) throw new TicketNotFoundException();
        return ticketMapper.toDto(ticket);
    }


    public TicketDto addTicket(TicketRequestDto ticketRequestDto) {
        var ticket = ticketMapper.toEntity(ticketRequestDto);
        ticketRepository.save(ticket);
        return ticketMapper.toDto(ticket);
    }

    public TicketDto updateTicket(Long id, UpdateTicketRequestDto updateTicketRequestDto) throws TicketNotFoundException {
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

    public TicketDto purchaseTicket(Long memberId, Long matchId, TicketDto ticketDto) throws TicketNotFoundException, MemberNotFoundException, MatchNotFoundException, TicketAlreadyExistException, MatchGoneException, FacilityNotFoundException, AllTicketPurchasesException {
        var member = memberRepository.findById(memberId).orElse(null);
        if(member == null) throw new MemberNotFoundException();
        var match = matchRepository.findById(matchId).orElse(null);
        if(match == null) throw new MatchNotFoundException();

        if (!ticketRepository.findByMatchIdAndMemberId(matchId, memberId).isEmpty())
            throw new TicketAlreadyExistException();
        if(!match.isExpired())
            throw new MatchGoneException();
        var facility = faciltyRepository.findById(match.getFacility().getId()).orElse(null);
        if(facility == null) throw new FacilityNotFoundException();
        if(facility.getCapacity() == match.getTickets().size() )
            throw new AllTicketPurchasesException();
        var ticket = ticketMapper.toEntity(ticketDto);
        ticket.setMatch(match);
        ticket.setMember(member);
        ticketRepository.save(ticket);
        return ticketMapper.toDto(ticket);
    }
}
