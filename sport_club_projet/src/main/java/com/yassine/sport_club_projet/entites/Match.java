package com.yassine.sport_club_projet.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "dateTime", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "opponentName", nullable = false, length = 100)
    private String opponentName;

    @Column(name = "scoreHome")
    private Integer scoreHome;

    @Column(name = "scoreAway")
    private Integer scoreAway;

    @Column(name = "result", length = 50)
    private String result;

    @Column(name = "newDate")
    private Instant newDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "facilityId")
    private Facility facility;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "teamId")
    private Team team;

    @OneToMany(mappedBy = "match")
    private Set<Attendance> attendances = new LinkedHashSet<>();

    @OneToMany(mappedBy = "match")
    private Set<Ticket> tickets = new LinkedHashSet<>();

    public Boolean isExpired() {
        return LocalDateTime.now().isAfter(dateTime);
    }

}