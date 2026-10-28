package com.yassine.sport_club_projet.repositories;

import com.yassine.sport_club_projet.dto.OauthProvider;
import com.yassine.sport_club_projet.entites.UserIdentity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserIdentityRepository extends JpaRepository<UserIdentity,Long> {
    @EntityGraph(attributePaths = "user")
    Optional<UserIdentity> findByProviderAndProviderUserId(OauthProvider provider, String providerUserId);

}
