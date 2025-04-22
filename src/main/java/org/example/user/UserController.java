package org.example.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.user.dto.SwaggerInterface;
import org.example.user.dto.req.ReqRegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@Tag(name = "회원", description = "회원 관련 API")
@RestController()
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @SwaggerInterface.RegisterUser
    @PostMapping("")
    public void registerUser(@RequestBody ReqRegisterUser payload) {
        service.registerUser(payload);
    }

}
