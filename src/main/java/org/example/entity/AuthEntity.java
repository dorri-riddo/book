package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "auths")
@Getter
@Setter
@NoArgsConstructor
public class AuthEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email", columnDefinition = "varchar", nullable = false)
    @Comment("이메일")
    private String email;

    @Column(name = "auth_code", columnDefinition = "varchar", nullable = false)
    @Comment("인증코드")
    private String authCode;

    @Column(name = "is_confirmed", columnDefinition = "boolean default false", nullable = false)
    @Comment("인증여부")
    private Boolean isConfirmed;

    @Column(name = "created_at", columnDefinition = "timestamp default CURRENT_TIME", nullable = false)
    @Comment("생성일")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public AuthEntity(String email, String authCode) {
        this.email = email;
        this.authCode = authCode;
        this.isConfirmed = false;
    }

    public boolean isExpired() {
        return this.createdAt.plusMinutes(3).isBefore(LocalDateTime.now());
    }

    public void confirmAuthCode() {
        this.isConfirmed = true;
    }

}
