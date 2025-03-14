package org.example.user;

import org.example.entity.AuthEntity;
import org.example.entity.UserEntity;
import org.example.util.exception.BadRequestException;
import org.example.repo.AuthRepository;
import org.example.repo.UserRepository;
import org.example.user.dto.req.ReqRegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @throws NoSuchAlgorithmException
     */
    public void registerUser(ReqRegisterUser payload) throws NoSuchAlgorithmException {
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
            throw new BadRequestException("E003", "invalid new user");
        }

        AuthEntity auth = authRepo.findTopByEmailAndIsConfirmedTrue(payload.getEmail());

        if (auth == null) {
            throw new BadRequestException("E003", "invalid new user");
        }

        if (auth.getIsConfirmed() == false) {
            throw new BadRequestException("E003", "invalid new user");
        }
    }

    /**
     * 신규 회원의 정보를 저장한다
     *
     * @param payload
     */
    private void saveUser(ReqRegisterUser payload) throws NoSuchAlgorithmException {
        String encryptPassword = passwordEncoder.encode(payload.getPassword());
        UserEntity createUserPayload = new UserEntity(payload.getName(), payload.getEmail(), encryptPassword);
        userRepo.save(createUserPayload);
    }
}
