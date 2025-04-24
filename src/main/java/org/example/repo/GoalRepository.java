package org.example.repo;

import jakarta.transaction.Transactional;
import org.example.entity.GoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<GoalEntity, Long> {
    @Transactional
    GoalEntity save(GoalEntity payload);

    GoalEntity findByIdAndUserIdAndDeletedAtIsNull(long id, long userId);

    @Transactional
    @Modifying
    @Query("UPDATE goals g SET g.dueDate = :#{#goal.dueDate}, g.state = :#{#goal.state}" +
            ", g.currentPage = :#{#goal.currentPage}, g.totalPage = :#{#goal.totalPage}, g.updatedAt = CURRENT_TIMESTAMP WHERE g.id = :id")
    int updateById(@Param("id") long id, @Param("goal") GoalEntity goal);

    @Transactional
    @Modifying
    @Query("UPDATE goals g SET g.currentPage = :currentPage, g.state = :state, g.updatedAt = CURRENT_TIMESTAMP WHERE g.id = :id")
    int updateCurrentPageAndStateById(@Param("id") long id, @Param("currentPage") long currentPage, @Param("state") String state);
}
