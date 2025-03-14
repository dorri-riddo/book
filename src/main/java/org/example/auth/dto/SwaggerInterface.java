package org.example.auth.dto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class SwaggerInterface {
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "인증 코드 이메일 발송 API", description = "회원가입에 필요한 인증코드 6자리를 이메일로 전송합니다.")
    public @interface SendAuthCodeByEmailSpecification {};

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "인증 코드 검증 API", description = "인증코드 6자리를 올바르게 입력하였는지 검증합니다.")
    public @interface ConfirmAuthCode {};

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "로그인 API", description = "사용자 정보를 확인하여 로그인합니다.")
    public @interface Login {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "토큰 리프레쉬 API", description = "리프레쉬 토큰을 확인하여 액세스 토큰을 재발급합니다.")
    public @interface RefreshAccessToken {}
}
