package org.example.user.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.example.entity.UserEntity;

@Getter
public class ReqRegisterUser {
    @Schema(description = "이름", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "이메일", example = "user@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(description = "비밀번호", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    public UserEntity toCreateUserEntity(String encryptPassword) {
        return UserEntity.builder()
                .name(this.name)
                .email(this.email)
                .password(encryptPassword)
                .build();
    }
}
