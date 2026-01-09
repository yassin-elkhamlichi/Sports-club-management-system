package com.yassine.sport_club_projet.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "SubscriptionPlan")
public class SubscriptionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "durationMonths", nullable = false)
    private Integer durationMonths;

    @Lob
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "plan"  , orphanRemoval = true)
    private Set<Subscription> subscriptions = new LinkedHashSet<>();


}