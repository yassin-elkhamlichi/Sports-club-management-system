package com.yassine.sport_club_projet.entites;

import com.yassine.sport_club_projet.dto.OauthProvider;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;


@Entity
@Table(name = "user_identities")
@Getter
@Setter
public class UserIdentity {
    @Id
    @Column(name = "user_id", nullable = false)
    private Long id;
    @Column
    @Enumerated(EnumType.STRING)
    private OauthProvider provider;
    @Column(name = "provider_user_id")
    private String providerUserId;
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
    @MapsId
    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
