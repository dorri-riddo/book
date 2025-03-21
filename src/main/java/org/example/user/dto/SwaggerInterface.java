package org.example.user.dto;

import io.swagger.v3.oas.annotations.Operation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class SwaggerInterface {
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "회원 신규 가입 API", description = "신규로 가입하는 회원을 등록한다")
    public @interface RegisterUser {};
}
