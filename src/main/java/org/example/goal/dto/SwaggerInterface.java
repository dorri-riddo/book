package org.example.goal.dto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class SwaggerInterface {
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "목표 목록 조회 API", description = "사용자의 독서 목표 목록을 조회한다", security = @SecurityRequirement(name = "bearerAuth"))
    public @interface GetGoals {};

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "목표 신규 등록 API", description = "수동으로 신규 독서 목표를 등록한다", security = @SecurityRequirement(name = "bearerAuth"))
    public @interface RegisterGoal {};

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "목표 수정 API", description = "수동으로 목표를 수정한다", security = @SecurityRequirement(name = "bearerAuth"))
    public @interface ModifyGoal {};

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "현재 읽고 있는 페이지 수정 API", description = "현재 읽고 있는 페이지를 수정한다", security = @SecurityRequirement(name = "bearerAuth"))
    public @interface ModifyGoalCurrentPage {}
}
