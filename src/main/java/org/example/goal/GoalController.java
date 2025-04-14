package org.example.goal;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.goal.dto.SwaggerInterface;
import org.example.goal.dto.req.ReqModifyGoalCurrentPage;
import org.example.goal.dto.req.ReqRegisterGoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "목표", description = "독서 목표 달성 관련 API")
@RestController()
@RequestMapping("/goal")
public class GoalController {
    @Autowired
    private GoalService service;

    @SwaggerInterface.RegisterGoal
    @PostMapping("")
    public void registerGoal(@RequestBody ReqRegisterGoal payload, Authentication auth) {
        long userId = Long.parseLong(auth.getName());

        service.registerGoal(payload, userId);
    }

    @SwaggerInterface.ModifyGoalCurrentPage
    @PatchMapping("/currentPage/{id}")
    public void modifyGoalCurrentPage(@PathVariable("id") long id, @RequestBody ReqModifyGoalCurrentPage payload, Authentication auth) {
        long userId = Long.parseLong(auth.getName());

        service.modifyGoalCurrentPage(id, payload, userId);
    }
}
