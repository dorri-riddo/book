package org.example.repo;

import jakarta.transaction.Transactional;
import org.example.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmailAndDeletedAtIsNull(String email);

    @Transactional
    UserEntity save(UserEntity payload);
}
