package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.MatchDto;
import com.yassine.sport_club_projet.dto.TicketRequestDto;
import com.yassine.sport_club_projet.dto.TicketResponseDto;
import com.yassine.sport_club_projet.dto.UpdateTicketRequestDto;
import com.yassine.sport_club_projet.entites.Match;
import com.yassine.sport_club_projet.entites.Member;
import com.yassine.sport_club_projet.entites.Ticket;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-26T01:11:52+0000",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class TicketMapperImpl implements TicketMapper {

    @Autowired
    private MatchMapper matchMapper;

    @Override
    public TicketResponseDto toDto(Ticket ticket) {
        if ( ticket == null ) {
            return null;
        }

        Long memberId = null;
        MatchDto matchDto = null;
        Long id = null;
        String qrCode = null;
        Boolean isUsed = null;
        BigDecimal price = null;

        memberId = ticketMemberId( ticket );
        matchDto = matchMapper.toDto( ticket.getMatch() );
        id = ticket.getId();
        qrCode = ticket.getQrCode();
        isUsed = ticket.getIsUsed();
        price = ticket.getPrice();

        TicketResponseDto ticketResponseDto = new TicketResponseDto( id, qrCode, isUsed, price, matchDto, memberId );

        return ticketResponseDto;
    }

    @Override
    public Ticket toDto(TicketResponseDto ticketResponseDto) {
        if ( ticketResponseDto == null ) {
            return null;
        }

        Ticket ticket = new Ticket();

        ticket.setMember( ticketResponseDtoToMember( ticketResponseDto ) );
        ticket.setId( ticketResponseDto.getId() );
        ticket.setQrCode( ticketResponseDto.getQrCode() );
        ticket.setPrice( ticketResponseDto.getPrice() );
        ticket.setIsUsed( ticketResponseDto.getIsUsed() );

        return ticket;
    }

    @Override
    public void update(UpdateTicketRequestDto updateTicketRequestDto, Ticket ticket) {
        if ( updateTicketRequestDto == null ) {
            return;
        }

        if ( ticket.getMatch() == null ) {
            ticket.setMatch( new Match() );
        }
        updateTicketRequestDtoToMatch( updateTicketRequestDto, ticket.getMatch() );
        if ( ticket.getMember() == null ) {
            ticket.setMember( new Member() );
        }
        updateTicketRequestDtoToMember( updateTicketRequestDto, ticket.getMember() );
        ticket.setPrice( updateTicketRequestDto.getPrice() );
        ticket.setIsUsed( updateTicketRequestDto.getIsUsed() );
    }

    @Override
    public Ticket toEntity(TicketRequestDto ticketRequestDto) {
        if ( ticketRequestDto == null ) {
            return null;
        }

        Ticket ticket = new Ticket();

        ticket.setId( ticketRequestDto.getId() );

        return ticket;
    }

    private Long ticketMemberId(Ticket ticket) {
        Member member = ticket.getMember();
        if ( member == null ) {
            return null;
        }
        return member.getId();
    }

    protected Member ticketResponseDtoToMember(TicketResponseDto ticketResponseDto) {
        if ( ticketResponseDto == null ) {
            return null;
        }

        Member member = new Member();

        member.setId( ticketResponseDto.getMemberId() );

        return member;
    }

    protected void updateTicketRequestDtoToMatch(UpdateTicketRequestDto updateTicketRequestDto, Match mappingTarget) {
        if ( updateTicketRequestDto == null ) {
            return;
        }

        mappingTarget.setId( updateTicketRequestDto.getMatchId() );
    }

    protected void updateTicketRequestDtoToMember(UpdateTicketRequestDto updateTicketRequestDto, Member mappingTarget) {
        if ( updateTicketRequestDto == null ) {
            return;
        }

        mappingTarget.setId( updateTicketRequestDto.getMemberId() );
    }
}
