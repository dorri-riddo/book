package org.example.util.jwt.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReqGenerateToken {
    private String userEmail;
    private long userId;
    private long expirationTime;
    private String tokenType;
}
