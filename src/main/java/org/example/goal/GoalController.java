package org.example.goal;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.goal.dto.SwaggerInterface;
import org.example.goal.dto.req.ReqModifyGoal;
import org.example.goal.dto.req.ReqModifyGoalCurrentPage;
import org.example.goal.dto.req.ReqRegisterGoal;
import org.example.goal.dto.resp.RespGoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "목표", description = "독서 목표 달성 관련 API")
@RestController()
@RequestMapping("/goal")
public class GoalController {
    @Autowired
    private GoalService service;

    @SwaggerInterface.GetGoals
    @GetMapping("")
    public List<RespGoal> getGoals(Authentication auth) {
        long userId = Long.parseLong(auth.getName());
        
        return service.getGoals(userId);
    }

    @SwaggerInterface.RegisterGoal
    @PostMapping("")
    public void registerGoal(@RequestBody ReqRegisterGoal payload, Authentication auth) {
        long userId = Long.parseLong(auth.getName());

        service.registerGoal(payload, userId);
    }

    @SwaggerInterface.ModifyGoal
    @PutMapping("{id}")
    public void modifyGoal(@PathVariable("id") long id, @RequestBody ReqModifyGoal payload, Authentication auth) {
        long userId = Long.parseLong(auth.getName());

        service.modifyGoal(id, payload, userId);
    }

    @SwaggerInterface.ModifyGoalCurrentPage
    @PatchMapping("/currentPage/{id}")
    public void modifyGoalCurrentPage(@PathVariable("id") long id, @RequestBody ReqModifyGoalCurrentPage payload, Authentication auth) {
        long userId = Long.parseLong(auth.getName());

        service.modifyGoalCurrentPage(id, payload, userId);
    }
}
