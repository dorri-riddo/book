package org.example.book.dto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class SwaggerInterface {
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "도서 목록 조회 API", description = "사용자의 도서 목록을 조회한다", security = @SecurityRequirement(name = "bearerAuth"))
    public @interface GetBookList {};


    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "도서 단일 조회 API", description = "도서 ID로 단일 도서를 조회한다", security = @SecurityRequirement(name = "bearerAuth"))
    public @interface GetBook {};

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "도서 신규 등록 API", description = "수동으로 신규 도서를 등록한다", security = @SecurityRequirement(name = "bearerAuth"))
    public @interface RegisterBook {};

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "도서 수정 API", description = "수동으로 도서를 수정한다", security = @SecurityRequirement(name = "bearerAuth"))
    public @interface ModifyBook {};

}
