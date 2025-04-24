package org.example.goal.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.example.entity.GoalEntity;

import java.time.LocalDateTime;

@Getter
public class ReqModifyGoal {
    @Schema(description = "목표일", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime dueDate;

    @Schema(description = "현재 읽고 있는 페이지", requiredMode = Schema.RequiredMode.REQUIRED)
    private long currentPage;

    @Schema(description = "전체 페이지", requiredMode = Schema.RequiredMode.REQUIRED)
    private long totalPage;

    public GoalEntity toModifyGoalEntity(String state) {
        return GoalEntity.builder()
                .dueDate(this.dueDate)
                .state(state)
                .currentPage(this.currentPage)
                .totalPage(this.totalPage)
                .build();
    }
}
