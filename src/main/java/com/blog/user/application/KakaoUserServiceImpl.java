package com.blog.user.application;

import com.blog.token.domain.Token;
import com.blog.token.domain.TokenRepository;
import com.blog.token.infrastructure.JwtTokenProvider;
import com.blog.token.presentation.dto.TokenResponse;
import com.blog.user.domain.User;
import com.blog.user.domain.UserRepository;
import com.blog.user.presentation.dto.KakaoLoginRequest;
import com.blog.user.presentation.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class KakaoUserServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public KakaoUserServiceImpl(
            UserRepository userRepository,
            TokenRepository tokenRepository,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public TokenResponse login(Object request) {
        KakaoLoginRequest kakaoRequest = (KakaoLoginRequest) request;
        String kakaoToken = kakaoRequest.getKakaoToken();

        // 1. 카카오 API에서 사용자 정보 조회 (지금은 MOCK)
        KakaoProfile profile = fetchKakaoProfile(kakaoToken);

        // 2. DB에서 이메일 기준으로 유저 검색
        User user = userRepository.findByEmail(profile.getEmail());
        Timestamp now = new Timestamp(System.currentTimeMillis());

        // 3. 없으면 자동 회원가입
        if (user == null) {
            user = new User(
                    null,
                    profile.getEmail(),
                    null, // 비밀번호 없음
                    profile.getName(),
                    null,
                    null,
                    profile.getProfileImage(),
                    null,
                    "kakao",
                    now,
                    now,
                    true
            );
            userRepository.save(user);
        }

        // 4. JWT 발급
        String accessToken = jwtTokenProvider.createAccessToken(user);
        String refreshToken = jwtTokenProvider.createRefreshToken(user);
        Timestamp expiresAt = jwtTokenProvider.getRefreshTokenExpiry();

        tokenRepository.save(new Token(user.getUserId(), refreshToken, expiresAt, now));

        // 5. 응답 생성
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());
        userResponse.setName(user.getName());
        userResponse.setProfileImage(user.getProfileImage());

        TokenResponse response = new TokenResponse();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        response.setExpiresAt(expiresAt.toString());
        response.setUser(userResponse);

        return response;
    }

    @Override
    public String getLoginType() {
        return "kakao";
    }

    private KakaoProfile fetchKakaoProfile(String token) {
        // ✅ 나중엔 여기서 진짜 카카오 API 호출 (RestTemplate 등)
        KakaoProfile profile = new KakaoProfile();
        profile.setEmail("kakao_user@example.com");
        profile.setName("카카오 유저");
        profile.setProfileImage("https://k.kakaocdn.net/profile.png");
        return profile;
    }

    // 내부 DTO 형태
    static class KakaoProfile {
        private String email;
        private String name;
        private String profileImage;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getProfileImage() { return profileImage; }
        public void setProfileImage(String profileImage) { this.profileImage = profileImage; }
    }
}
