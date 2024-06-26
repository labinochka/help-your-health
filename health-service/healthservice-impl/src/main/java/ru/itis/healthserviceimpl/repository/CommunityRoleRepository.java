package ru.itis.healthserviceimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.healthserviceimpl.model.CommunityRole;
import ru.itis.healthserviceimpl.model.roles.CommunityRoleType;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommunityRoleRepository extends JpaRepository<CommunityRole, UUID> {
    Optional<CommunityRole> findByType(CommunityRoleType type);
}
