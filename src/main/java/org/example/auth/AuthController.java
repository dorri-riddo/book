package org.example.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.auth.dto.SwaggerInterface;
import org.example.auth.dto.req.ReqConfirmAuthCode;
import org.example.auth.dto.req.ReqLogIn;
import org.example.auth.dto.req.ReqRefreshAccessToken;
import org.example.auth.dto.req.ReqSendAuthCodeByEmail;
import org.example.auth.dto.resp.RespLogIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "인증", description = "인증 관련 API")
@RestController()
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @SwaggerInterface.SendAuthCodeByEmailSpecification
    @PostMapping("/email")
    public void sendAuthCodeByEmail(@RequestBody ReqSendAuthCodeByEmail payload) {
        service.sendAuthCodeByEmail(payload);
    }

    @SwaggerInterface.ConfirmAuthCode
    @PostMapping("/validate")
    public void confirmAuthCode(@RequestBody ReqConfirmAuthCode payload) {
        service.confirmAuthCode(payload);
    }

    @SwaggerInterface.Login
    @PostMapping("/logIn")
    public RespLogIn logIn(@RequestBody ReqLogIn payload) {
        return service.logIn(payload);
    }

    @SwaggerInterface.RefreshAccessToken
    @PostMapping("/refresh")
    public RespLogIn refreshAccessToken(@RequestBody ReqRefreshAccessToken payload) throws Exception {
        return service.refreshAccessToken(payload);
    }
}
