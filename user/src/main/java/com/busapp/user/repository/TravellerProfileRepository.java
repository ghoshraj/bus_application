package com.busapp.user.repository;

import com.busapp.user.entity.TravellerProfiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TravellerProfileRepository extends JpaRepository<TravellerProfiles, Integer> {

    Optional<TravellerProfiles> findByUserId(Integer userId);
}
