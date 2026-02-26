package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.MemberResponseDto;
import com.yassine.sport_club_projet.dto.SubscriptionDto;
import com.yassine.sport_club_projet.dto.TicketResponseDto;
import com.yassine.sport_club_projet.dto.UpdateMemberRequestDto;
import com.yassine.sport_club_projet.dto.UserMemberRequestDto;
import com.yassine.sport_club_projet.entites.Member;
import com.yassine.sport_club_projet.entites.Subscription;
import com.yassine.sport_club_projet.entites.User;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-26T01:10:22+0000",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public MemberResponseDto toDto(Member member) {
        if ( member == null ) {
            return null;
        }

        Long user = null;
        Long id = null;
        LocalDate birthDate = null;
        Integer loyaltyPoints = null;
        Boolean isActive = null;
        Set<SubscriptionDto> subscriptions = null;

        user = memberUserId( member );
        id = member.getId();
        birthDate = member.getBirthDate();
        loyaltyPoints = member.getLoyaltyPoints();
        isActive = member.getIsActive();
        subscriptions = subscriptionSetToSubscriptionDtoSet( member.getSubscriptions() );

        Set<TicketResponseDto> ticketsId = null;

        MemberResponseDto memberResponseDto = new MemberResponseDto( id, user, birthDate, loyaltyPoints, isActive, subscriptions, ticketsId );

        return memberResponseDto;
    }

    @Override
    public Member toEntity(UserMemberRequestDto userMemberRequestDto) {
        if ( userMemberRequestDto == null ) {
            return null;
        }

        Member member = new Member();

        if ( userMemberRequestDto.getBirthDate() != null ) {
            member.setBirthDate( LocalDate.parse( userMemberRequestDto.getBirthDate() ) );
        }

        return member;
    }

    @Override
    public void update(UpdateMemberRequestDto updateUserRequestDto, Member member) {
        if ( updateUserRequestDto == null ) {
            return;
        }

        member.setBirthDate( updateUserRequestDto.getBirthDate() );
    }

    private Long memberUserId(Member member) {
        User user = member.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    protected SubscriptionDto subscriptionToSubscriptionDto(Subscription subscription) {
        if ( subscription == null ) {
            return null;
        }

        LocalDate startDate = null;
        LocalDate endDate = null;
        String status = null;

        startDate = subscription.getStartDate();
        endDate = subscription.getEndDate();
        status = subscription.getStatus();

        Long planId = null;
        Long memberId = null;

        SubscriptionDto subscriptionDto = new SubscriptionDto( startDate, endDate, status, planId, memberId );

        return subscriptionDto;
    }

    protected Set<SubscriptionDto> subscriptionSetToSubscriptionDtoSet(Set<Subscription> set) {
        if ( set == null ) {
            return null;
        }

        Set<SubscriptionDto> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( Subscription subscription : set ) {
            set1.add( subscriptionToSubscriptionDto( subscription ) );
        }

        return set1;
    }
}
