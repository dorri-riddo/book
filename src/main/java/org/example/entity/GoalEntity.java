package org.example.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity(name = "goals")
@Getter
@Builder
public class GoalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id", columnDefinition = "int8", nullable = false)
    @Comment("사용자 id")
    private long userId;

    @Column(name = "book_id", columnDefinition = "int8", nullable = false)
    @Comment("책 id")
    private long bookId;

    @Column(name = "due_date", columnDefinition = "timestamp", nullable = false)
    @Comment("목표일")
    private LocalDateTime dueDate;

    @Column(name = "state", columnDefinition = "varchar default planned", nullable = false)
    @Comment("상태")
    private String state;

    @Column(name = "current_page", columnDefinition = "int8", nullable = false)
    @Comment("현재 읽고 있는 페이지")
    private long currentPage;

    @Column(name = "total_page", columnDefinition = "int8", nullable = false)
    @Comment("전체 페이지")
    private long totalPage;

    @Column(name = "created_at", columnDefinition = "timestamp default CURRENT_TIME", nullable = false)
    @Comment("생성일")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "timestamp default CURRENT_TIME", nullable = false)
    @Comment("수정일")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "timestamp", nullable = true)
    @Comment("삭제일")
    private LocalDateTime deletedAt;
}
