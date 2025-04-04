package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", columnDefinition = "varchar", nullable = false)
    @Comment("이름")
    private String name;

    @Column(name = "email", columnDefinition = "varchar", nullable = false, unique = true)
    @Comment("이메일")
    private String email;

    @Column(name = "password", columnDefinition = "varchar", nullable = false)
    @Comment("비밀번호")
    private String password;

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
