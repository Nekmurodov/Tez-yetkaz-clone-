package com.example.Tez_Yetkaz.repository;


import com.example.Tez_Yetkaz.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmailAndDeletedFalse(String email);
    boolean existsByEmailAndDeletedFalse(String email);
    Optional<User> findByIdAndDeletedFalse(UUID id);

    Page<User> findAllByDeletedFalse(Pageable pageable);

}
