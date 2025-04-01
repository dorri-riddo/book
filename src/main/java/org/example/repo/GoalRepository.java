package org.example.repo;

import jakarta.transaction.Transactional;
import org.example.entity.GoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<GoalEntity, Long> {
    @Transactional
    GoalEntity save(GoalEntity payload);
}
