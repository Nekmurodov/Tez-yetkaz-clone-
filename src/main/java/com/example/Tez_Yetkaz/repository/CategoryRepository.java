package com.example.Tez_Yetkaz.repository;

import com.example.Tez_Yetkaz.entity.fr.Category;
import com.example.Tez_Yetkaz.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    boolean existsByIdAndDeletedFalse(UUID id);
    Optional<Category> findByIdAndDeletedFalse(UUID id);
    List<Category> findAllByDeletedFalse();
    List<Category> findAllByRestaurantIdAndDeletedFalse(UUID restaurantId);
    Optional<Category> findByIdAndDeletedFalseAndCategoryType(UUID id, CategoryType categoryType);
    List<Category> findAllByDeletedFalseAndCategoryType(CategoryType categoryType);
}
