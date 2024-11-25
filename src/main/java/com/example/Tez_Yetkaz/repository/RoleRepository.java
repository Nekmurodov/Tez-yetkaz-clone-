package com.example.Tez_Yetkaz.repository;

import com.example.Tez_Yetkaz.entity.user.Role;
import com.example.Tez_Yetkaz.enums.RoleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByRoleType(RoleType roleType);

    Optional<Role> findByIdAndDeletedFalse(UUID roleId);

    Page<Role> findAllByDeletedFalse(Pageable pageable);

    List<Role> findAllByDeletedFalse();

}
