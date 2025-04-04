package com.blog.user.presentation;

import com.blog.user.application.UserService;
import com.blog.user.presentation.dto.EmailLoginRequest;
import com.blog.token.presentation.dto.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final Map<String, UserService> loginStrategies;

    public UserController(List<UserService> services) {
        this.loginStrategies = services.stream()
                .collect(Collectors.toMap(
                        UserService::getLoginType,
                        Function.identity()
                ));
    }

    @PostMapping("/email")
    public ResponseEntity<TokenResponse> loginByEmail(@RequestBody EmailLoginRequest request) {
        UserService service = loginStrategies.get("email");

        if (service == null) {
            return ResponseEntity.badRequest().build();
        }

        TokenResponse response = service.login(request);
        return ResponseEntity.ok(response);
    }

  /*
  @PostMapping("/kakao")
  public ResponseEntity<TokenResponse> loginByKakao(@RequestBody KakaoLoginRequest request) {
    UserService service = loginStrategies.get("kakao");
    ...
  }
  */
}
