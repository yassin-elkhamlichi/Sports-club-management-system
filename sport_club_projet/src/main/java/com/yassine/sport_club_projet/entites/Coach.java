package com.yassine.sport_club_projet.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Getter
@Setter
@Entity
@Table(name = "Coach")
public class Coach {
    @Id
    @Column(name = "userId", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(name = "specialization", length = 100)
    private String specialization;

    @Column(name = "certificateLevel", length = 100)
    private String certificateLevel;

    @OneToMany(mappedBy = "coach")
    private Set<Team> teams = new LinkedHashSet<>();


    public Boolean findTeam(Long id) {
        AtomicReference<Boolean> a = new AtomicReference<>(false);
        teams.forEach(
                team -> {
                    if (team.getId().equals(id)) {
                        a.set(true);
                    }
                }
        );
        return a.get();
    }
}