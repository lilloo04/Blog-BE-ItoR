package com.blog.user.application;

import com.blog.user.presentation.dto.EmailLoginRequest;
import com.blog.token.presentation.dto.TokenResponse;
import com.blog.user.presentation.dto.UserResponse;
import com.blog.user.presentation.dto.UserSignupRequest;

public interface UserService {
    TokenResponse login(EmailLoginRequest request);
    String getLoginType(); // ex) "email", "kakao"
    UserResponse signup(UserSignupRequest request);
}
