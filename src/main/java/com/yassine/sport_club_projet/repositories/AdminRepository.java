package com.yassine.sport_club_projet.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.yassine.sport_club_projet.entites.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
