package org.example.auth.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ReqLogIn {
    @Schema(description = "이메일", example = "user@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(description = "비밀번호", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
