package com.example.Tez_Yetkaz.repository;

import com.example.Tez_Yetkaz.entity.fr.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {

    Optional<Restaurant> findByIdAndDeletedFalse(UUID id);

    boolean existsByIdAndDeletedFalse(UUID id);

    Page<Restaurant> findAllByDeletedFalse(Pageable pageable);

    Page<Restaurant> findAllByDeletedFalseAndCategoryId(Pageable pageable, UUID categoryId);

    @Query("SELECT r FROM Restaurant r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Restaurant> findByNameContainingIgnoreCase(@Param("name") String name);

}
