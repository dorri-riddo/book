package org.example.auth.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ReqRefreshAccessToken {
    @Schema(description = "리프레쉬 토큰", requiredMode = Schema.RequiredMode.REQUIRED)
    private String refreshToken;
}
