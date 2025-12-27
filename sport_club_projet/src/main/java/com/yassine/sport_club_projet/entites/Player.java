package com.yassine.sport_club_projet.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "jerseyNumber")
    private Integer jerseyNumber;

    @Column(name = "birthDate", nullable = false)
    private LocalDate birthDate;

    @Column(name = "position", length = 50)
    private String position;

    @Column(name = "medicalCertificate")
    private String medicalCertificate;

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "teamId")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "memberId")
    private Member member;

    @OneToMany(mappedBy = "player")
    private Set<Attendance> attendances = new LinkedHashSet<>();


}