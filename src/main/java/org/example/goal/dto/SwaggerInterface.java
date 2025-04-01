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
    @Operation(summary = "목표 신규 등록 API", description = "수동으로 신규 독서 목표를 등록한다", security = @SecurityRequirement(name = "bearerAuth"))
    public @interface RegisterGoal {};
}
