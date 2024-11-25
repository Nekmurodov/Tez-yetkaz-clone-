package com.example.Tez_Yetkaz.repository;

import com.example.Tez_Yetkaz.entity.fr.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {

    Optional<Restaurant> findByIdAndDeletedFalse(UUID id);

    Page<Restaurant> findAllByDeletedFalse(Pageable pageable);
}
