package com.yassine.sport_club_projet.dto;

import com.yassine.sport_club_projet.entites.Subscription;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@Data
public class SubscriptionPlanDto {

    private Long id;

    private String name;

    private BigDecimal price;

    private Integer durationMonths;


    private String description;


}
