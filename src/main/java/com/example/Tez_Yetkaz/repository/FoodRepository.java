package com.example.Tez_Yetkaz.repository;

import com.example.Tez_Yetkaz.entity.fr.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FoodRepository extends JpaRepository<Food, UUID> {

    Optional<Food> findByIdAndDeletedFalse(UUID id);

    Page<Food> findAllByDeletedFalse(Pageable pageable);
}
