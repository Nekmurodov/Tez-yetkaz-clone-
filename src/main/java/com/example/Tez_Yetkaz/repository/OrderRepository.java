package com.example.Tez_Yetkaz.repository;

import com.example.Tez_Yetkaz.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    Optional<Order> findByIdAndDeletedFalse(UUID orderId);
    Page<Order> findAllByDeletedFalse(Pageable pageable);

    Page<Order> findAllByDeletedFalseAndDeliverTrue(Pageable pageable);
    Page<Order> findAllByDeletedFalseAndDeliverFalseAndUserIdAndStatusTrue(Pageable pageable, UUID userId);

    Page<Order> findAllByUserIdAndDeletedFalse(Pageable pageable, UUID userId);

}
