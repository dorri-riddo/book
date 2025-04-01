package org.example.goal.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.example.entity.GoalEntity;

import java.time.LocalDateTime;

@Getter
public class ReqRegisterGoal {
    @Schema(description = "책 id", requiredMode = Schema.RequiredMode.REQUIRED)
    private long bookId;

    @Schema(description = "목표일", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime dueDate;

    @Schema(description = "전체 페이지", requiredMode = Schema.RequiredMode.REQUIRED)
    private long totalPage;

    public GoalEntity toCreateGoalEntity(long userId) {
        return GoalEntity.builder()
                .userId(userId)
                .bookId(this.bookId)
                .dueDate(this.dueDate)
                .state("planned")
                .currentPage(0)
                .totalPage(this.totalPage)
                .build();
    }
}
