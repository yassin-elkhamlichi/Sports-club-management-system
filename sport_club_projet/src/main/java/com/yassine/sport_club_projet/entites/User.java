package com.yassine.sport_club_projet.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "firstname", nullable = false, length = 100)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 100)
    private String lastname;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "role", nullable = false, length = 50)
    private String role;

    @OneToOne(mappedBy = "user")
    private Admin admin;

    @OneToOne(mappedBy = "user")
    private Coach coach;

    @OneToOne(mappedBy = "user")
    private Member member;


}