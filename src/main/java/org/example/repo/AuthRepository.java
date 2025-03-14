package org.example.repo;

import jakarta.transaction.Transactional;
import org.example.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, Long> {
    @Transactional
    AuthEntity save(AuthEntity payload);

    AuthEntity findTopByEmailOrderByIdDesc(String email);

    AuthEntity findTopByEmailAndIsConfirmedTrue(String email);
}
