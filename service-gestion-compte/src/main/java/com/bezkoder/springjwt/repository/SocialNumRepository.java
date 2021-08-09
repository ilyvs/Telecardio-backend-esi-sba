package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.SocialNum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocialNumRepository extends JpaRepository<SocialNum, Long> {
    Optional<SocialNum> findBySin(SocialNum sin);

    Boolean existsBySin(Long sin);

}
