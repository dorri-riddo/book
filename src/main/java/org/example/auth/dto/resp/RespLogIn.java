package org.example.auth.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RespLogIn {
    @Schema(description = "액세스 토큰")
    private String accessToken;
    @Schema(description = "리프레쉬 토큰")
    private String refreshToken;
}
