package org.example.auth.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ReqConfirmAuthCode {
    @Schema(description = "이메일", example = "user@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(description = "인증코드", example = "000000", requiredMode = Schema.RequiredMode.REQUIRED)
    private String authCode;
}
