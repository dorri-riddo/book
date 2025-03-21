package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity(name = "books")
@Getter
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", columnDefinition = "varchar", nullable = false)
    @Comment("제목")
    private String title;

    @Column(name = "publisher", columnDefinition = "varchar", nullable = true)
    @Comment("출판사")
    private String publisher;

    @Column(name = "isbn", columnDefinition = "varchar", nullable = true)
    @Comment("isbn")
    private String isbn;

    @Column(name = "author", columnDefinition = "varchar", nullable = false)
    @Comment("저자")
    private String author;

    @Column(name = "cover_image_url", columnDefinition = "varchar", nullable = true)
    @Comment("커버 이미지")
    private String coverImageUrl;

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
