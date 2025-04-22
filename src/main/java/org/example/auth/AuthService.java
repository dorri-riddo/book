package org.example.auth;

import org.example.auth.dto.req.ReqConfirmAuthCode;
import org.example.auth.dto.req.ReqLogIn;
import org.example.auth.dto.req.ReqRefreshAccessToken;
import org.example.auth.dto.req.ReqSendAuthCodeByEmail;
import org.example.auth.dto.resp.RespLogIn;
import org.example.repo.AuthRepository;
import org.example.repo.UserRepository;
import org.example.entity.AuthEntity;
import org.example.util.exception.ResponseCustomStatusException;
import org.example.util.jwt.JwtTokenProvider;
import org.example.entity.UserEntity;
import org.example.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Map;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private AuthRepository authRepo;
    @Autowired
    private MailService mailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private static final SecureRandom Random = new SecureRandom();
    private static final int CODE_LENGTH = 6;

    /**
     * 이메일로 회원가입 인증번호를 전송한다
     *
     * @param payload
     */
    public void sendAuthCodeByEmail(ReqSendAuthCodeByEmail payload) {
        // 검증
        this.validateSendAuthCodeByEmail(payload);

        // 인증 코드 생성
        String code = this.generateAuthCode();

        // 인증 코드 저장
        this.saveAuthCode(payload.getEmail(), code);

        // 인증 코드 이메일 전송
        mailService.sendAuthCode(payload.getEmail(), code);
    }

    /**
     * 인증코드를 검증한다
     *
     * @param payload
     */
    public void confirmAuthCode(ReqConfirmAuthCode payload) {
        // 인증코드 검증
        AuthEntity auth = authRepo.findTopByEmailOrderByIdDesc(payload.getEmail());
        this.validateAuthCode(auth, payload.getAuthCode());

        // 검증이 완료되면 확인된 코드임을 업데이트한다
        auth.confirmAuthCode();
        authRepo.save(auth);
    }

    /**
     * 사용자 로그인
     * @param payload
     * @return
     */
    public RespLogIn logIn(ReqLogIn payload) {
        // 로그인 페이로드 정보를 검증한다
        UserEntity user = userRepo.findByEmailAndDeletedAtIsNull(payload.getEmail());
        validateLogIn(payload, user);

        // 토큰을 응답한다
        return generateTokenForLogInUser(user);
    }

    /**
     * 리프레쉬 토큰을 기반으로 액세스 토큰을 신규 발급한다
     * @param payload
     * @return
     */
    public RespLogIn refreshAccessToken(ReqRefreshAccessToken payload) {
        String newAccessToken = jwtTokenProvider.refreshAccessToken(payload.getRefreshToken());
        return new RespLogIn(newAccessToken, payload.getRefreshToken());
    }

    public void logOut() {

    }

    /**
     * 인증코드를 이메일로 전송하기 전에 검증한다
     *
     * @param payload
     */
    private void validateSendAuthCodeByEmail(ReqSendAuthCodeByEmail payload) {
        UserEntity user = userRepo.findByEmailAndDeletedAtIsNull(payload.getEmail());
        if (user != null) {
            throw new ResponseCustomStatusException("already existed email", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 인증코드를 생성한다
     *
     * @return authCode
     */
    private String generateAuthCode() {
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            int digit = Random.nextInt(10);
            codeBuilder.append(digit);
        }

        return codeBuilder.toString();
    }

    /**
     * 이메일과 그에 받은 인증코드를 저장한다
     *
     * @param email
     * @param code
     */
    private void saveAuthCode(String email, String code) {
        AuthEntity createAuthPayload = new AuthEntity(email, code);
        authRepo.save(createAuthPayload);
    }

    /**
     * 올바른 인증코드인지 검증한다
     *
     * @param auth
     * @param code
     */
    private void validateAuthCode(AuthEntity auth, String code) {
        if (auth == null) {
            throw new ResponseCustomStatusException("invalid auth code", HttpStatus.BAD_REQUEST);

        }

        if (auth.isExpired()) {
            throw new ResponseCustomStatusException("invalid auth code", HttpStatus.BAD_REQUEST);
        }

        if (!code.equals(auth.getAuthCode())) {
            throw new ResponseCustomStatusException("invalid auth code", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 로그인에 대한 아이디와 비밀번호를 검증한다
     * @param payload
     */
    private void validateLogIn(ReqLogIn payload, UserEntity user) {
        // 이메일을 검증한다
        if (user == null) {
            throw new ResponseCustomStatusException("invalid email", HttpStatus.BAD_REQUEST, "E001");
        }

        // 비밀번호를 검증한다
        Boolean passwordMatch = passwordEncoder.matches(payload.getPassword(), user.getPassword());
        if (passwordMatch == false) {
            throw new ResponseCustomStatusException("invalid email", HttpStatus.BAD_REQUEST, "E002");
        }
    }

    /**
     * 로그인한 사용자 정보를 기반으로 토큰을 응답한다
     * @param user
     * @return
     */
    private RespLogIn generateTokenForLogInUser(UserEntity user) {
        Map<String, String> tokens = jwtTokenProvider.generateTokens(user.getEmail(), user.getId());
        String accessToken = tokens.get("accessToken");
        String refreshToken = tokens.get("refreshToken");

        return new RespLogIn(accessToken, refreshToken);
    }
}
