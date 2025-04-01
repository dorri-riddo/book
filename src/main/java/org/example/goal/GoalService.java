package org.example.goal;

import org.example.entity.GoalEntity;
import org.example.goal.dto.req.ReqRegisterGoal;
import org.example.repo.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalService {
    @Autowired
    private GoalRepository goalRepo;

    public void registerGoal(ReqRegisterGoal payload, long userId) {
        GoalEntity createGoalEntity = payload.toCreateGoalEntity(userId);

        goalRepo.save(createGoalEntity);
    }
}
