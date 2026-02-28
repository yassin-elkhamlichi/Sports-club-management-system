package com.yassine.sport_club_projet.services;

import com.yassine.sport_club_projet.dto.SubscriptionDto;
import com.yassine.sport_club_projet.entites.Member;
import com.yassine.sport_club_projet.entites.Role;
import com.yassine.sport_club_projet.entites.User;
import com.yassine.sport_club_projet.exceptions.MemberNotFoundException;
import com.yassine.sport_club_projet.repositories.MemberRepository;
import com.yassine.sport_club_projet.repositories.SubscriptionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional   // Rolls back database changes after each test
public class SubscriptionServiceIT {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Test
    void addSubscription_ShouldActuallySaveInDatabase() throws Exception, MemberNotFoundException {
        // 1. Arrange: Save a real member to the H2 database
        Member member = new Member();
        User user = new User();
        user.setFirstname("Yassine");
        user.setLastname("khamlichi");
        user.setEmail("yassine@test.com");
        user.setRole(Role.MEMBER);
        user.setPassword("hhhhhhhh");
        member.setUser(user);
        member.setBirthDate(LocalDate.of(1990, 5, 15));
        Member savedMember = memberRepository.save(member);

        SubscriptionDto dto = new SubscriptionDto();
        dto.setStartDate(LocalDate.now());
        dto.setEndDate(LocalDate.now().plusMonths(1));
        dto.setStatus("ACTIVE");
        // 2. Act: Call the service
        SubscriptionDto result = subscriptionService.addSubscription(dto, savedMember.getId());

        // 3. Assert: Check if it's really in the database
        assertNotNull(result);
        assertEquals(1, subscriptionRepository.count()); // The DB should have 1 record now

        var savedInDb = subscriptionRepository.findAll().getFirst();
        assertEquals("Yassine", savedInDb.getMember().getUser().getFirstname());
    }
}