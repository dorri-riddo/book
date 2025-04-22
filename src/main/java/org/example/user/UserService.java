package org.example.user;

import org.example.entity.AuthEntity;
import org.example.entity.UserEntity;
import org.example.repo.AuthRepository;
import org.example.repo.UserRepository;
import org.example.user.dto.req.ReqRegisterUser;
import org.example.util.exception.ResponseCustomStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private AuthRepository authRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 신규 사용자를 등록한다
     *
     * @param payload
     */
    public void registerUser(ReqRegisterUser payload) {
        // 검증
        validateRegisterUser(payload);

        //  신규 user 생성
        saveUser(payload);
    }

    /**
     * 신규 사용자 정보를 검증한다
     *
     * @param payload
     */
    private void validateRegisterUser(ReqRegisterUser payload) {
        UserEntity user = userRepo.findByEmailAndDeletedAtIsNull(payload.getEmail());

        if (user != null) {
            throw new ResponseCustomStatusException("invalid new user", HttpStatus.BAD_REQUEST);
        }

        AuthEntity auth = authRepo.findTopByEmailAndIsConfirmedTrue(payload.getEmail());

        if (auth == null) {
            throw new ResponseCustomStatusException("invalid new user", HttpStatus.BAD_REQUEST);
        }

        if (auth.getIsConfirmed() == false) {
            throw new ResponseCustomStatusException("invalid new user", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 신규 회원의 정보를 저장한다
     *
     * @param payload
     */
    private void saveUser(ReqRegisterUser payload) {
        String encryptPassword = passwordEncoder.encode(payload.getPassword());
        UserEntity createUserPayload = payload.toCreateUserEntity(encryptPassword);
        userRepo.save(createUserPayload);
    }
}
