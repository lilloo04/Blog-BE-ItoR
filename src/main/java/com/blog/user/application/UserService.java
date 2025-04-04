package com.blog.user.application;

import com.blog.user.presentation.dto.EmailLoginRequest;
import com.blog.token.presentation.dto.TokenResponse;

public interface UserService {
    TokenResponse login(EmailLoginRequest request);
    String getLoginType(); // ex) "email", "kakao"
}
