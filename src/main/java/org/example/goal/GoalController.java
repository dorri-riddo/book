package org.example.goal;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.goal.dto.SwaggerInterface;
import org.example.goal.dto.req.ReqRegisterGoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
