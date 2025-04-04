package org.example.goal.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ReqModifyGoalCurrentPage {
    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED)
    private long goalId;

    @Schema(description = "현재 읽고 있는 페이지", requiredMode = Schema.RequiredMode.REQUIRED)
    private long currentPage;
}
