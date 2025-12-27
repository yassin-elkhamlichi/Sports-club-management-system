package com.yassine.sport_club_projet.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Member")
public class Member {
    @Id
    @Column(name = "userId", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(name = "birthDate", nullable = false)
    private LocalDate birthDate;

    @ColumnDefault("0")
    @Column(name = "loyaltyPoints")
    private Integer loyaltyPoints;

    @ColumnDefault("1")
    @Column(name = "isActive")
    private Boolean isActive;

    @OneToMany(mappedBy = "member")
    private Set<Player> players = new LinkedHashSet<>();

    @OneToMany(mappedBy = "member")
    private Set<Subscription> subscriptions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "member")
    private Set<Ticket> tickets = new LinkedHashSet<>();


}