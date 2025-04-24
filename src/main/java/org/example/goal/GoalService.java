package org.example.goal;

import org.example.entity.GoalEntity;
import org.example.goal.dto.req.ReqModifyGoal;
import org.example.goal.dto.req.ReqModifyGoalCurrentPage;
import org.example.goal.dto.req.ReqRegisterGoal;
import org.example.repo.GoalRepository;
import org.example.util.exception.ResponseCustomStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class GoalService {
    @Autowired
    private GoalRepository goalRepo;

    public void registerGoal(ReqRegisterGoal payload, long userId) {
        GoalEntity createGoalEntity = payload.toCreateGoalEntity(userId);

        goalRepo.save(createGoalEntity);
    }

    public void modifyGoal(long id, ReqModifyGoal payload, long userId) {
        GoalEntity goal = goalRepo.findByIdAndUserIdAndDeletedAtIsNull(id, userId);
        if (goal == null) {
            throw new ResponseCustomStatusException("not found goal", HttpStatus.NOT_FOUND);
        }

        String currentState = getGoalState(payload.getCurrentPage(), payload.getTotalPage());
        GoalEntity modifyGoalEntity = payload.toModifyGoalEntity(currentState);

        goalRepo.updateById(id, modifyGoalEntity);
    }

    public void modifyGoalCurrentPage(long id, ReqModifyGoalCurrentPage payload, long userId) {
        GoalEntity goal = goalRepo.findByIdAndUserIdAndDeletedAtIsNull(id, userId);
        if (goal == null) {
            throw new ResponseCustomStatusException("not found goal", HttpStatus.NOT_FOUND);
        }

        String currentState = getGoalState(payload.getCurrentPage(), goal.getTotalPage());
        goalRepo.updateCurrentPageAndStateById(id, payload.getCurrentPage(), currentState);
    }

    private String getGoalState(long currentPage, long totalPage) {
        if (currentPage == 0) {
            return "planned";
        }

        if (currentPage > 0 && currentPage < totalPage) {
            return "inProgress";
        }

        return "finished";
    }
}
