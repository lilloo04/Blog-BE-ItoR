package com.blog.user.application;

import com.blog.token.presentation.dto.TokenResponse;
import com.blog.user.presentation.dto.EmailLoginRequest;
import com.blog.token.domain.Token;
import com.blog.token.domain.TokenRepository;
import com.blog.user.domain.User;
import com.blog.user.domain.UserRepository;
import com.blog.token.infrastructure.JwtTokenProvider;
import com.blog.user.presentation.dto.UserResponse;

import java.sql.Timestamp;

public class EmailUserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public EmailUserServiceImpl(
            UserRepository userRepository,
            TokenRepository tokenRepository,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public TokenResponse login(EmailLoginRequest request) {
        // 1. 사용자 조회
        User user = userRepository.findByEmail(request.getEmail());

        if (user == null || !user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        // 2. 토큰 생성
        String accessToken = jwtTokenProvider.createAccessToken(user);
        String refreshToken = jwtTokenProvider.createRefreshToken(user);
        Timestamp expiresAt = jwtTokenProvider.getRefreshTokenExpiry();

        // 3. 토큰 저장
        Token token = new Token(user.getUserId(), refreshToken, expiresAt, new Timestamp(System.currentTimeMillis()));
        tokenRepository.save(token);

        // 4. 응답 DTO 생성
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());
        userResponse.setName(user.getName());
        userResponse.setProfileImage(user.getProfileImage());

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setUser(userResponse);

        return tokenResponse;
    }

    @Override
    public String getLoginType() {
        return "email";
    }
}
