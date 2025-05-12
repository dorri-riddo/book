package org.example.goal.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import org.example.entity.GoalEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class RespGoal {
    @Schema(description = "목표 id")
    private long id;

    @Schema(description = "책 id")
    private long bookId;

    @Schema(description = "책 제목")
    private String bookTitle;

    @Schema(description = "목표일")
    private LocalDateTime dueDate;

    @Schema(description = "상태")
    private String state;

    @Schema(description = "현재 읽고 있는 페이지")
    private long currentPage;

    @Schema(description = "전체 페이지")
    private long totalPage;

    @Schema(description = "생성일")
    private LocalDateTime createdAt;

    @Schema(description = "수정일")
    private LocalDateTime updatedAt;

    public RespGoal(long id, long bookId, String bookTitle, LocalDateTime dueDate, String state, long currentPage, long totalPage, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.dueDate = dueDate;
        this.state = state;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
} 