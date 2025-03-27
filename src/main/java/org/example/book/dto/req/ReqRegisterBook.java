package org.example.book.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.example.entity.BookEntity;

@Getter
public class ReqRegisterBook {
    @Schema(description = "제목", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(description = "출판사", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String publisher;

    @Schema(description = "isbn", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String isbn;

    @Schema(description = "저자", requiredMode = Schema.RequiredMode.REQUIRED)
    private String author;

    @Schema(description = "커버 이미지", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String coverImageUrl;

    @Schema(description = "전체 페이지", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long totalPage;

    public BookEntity toCreateBookEntity(long userId) {
        return BookEntity.builder()
                .userId(userId)
                .title(this.title)
                .publisher(this.publisher)
                .isbn(this.isbn)
                .author(this.author)
                .coverImageUrl(this.coverImageUrl)
                .totalPage(this.totalPage)
                .build();
    }
}
