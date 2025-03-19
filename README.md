# Blog-BE-ItoR

# Blog 만들기

## 미션 요구사항

1. 미션 진행 방법을 꼭 읽고 진행해주세요
   [미션 진행 방법](https://www.notion.so/46dbd9440a4f4d5e97228011dff70f5a?pvs=21)
2. 해당 ReadMe 를 지우고 erd 및 디렉토리 구조를 작성해주세요
    1. erd를 그리는 방법은 자유입니다
    2. 디렉토리 구조도 자유롭게 설정하셔도 좋습니다

예시

```
├── HELP.md
├── README.md
├── build
│   ├── classes
│   │   └── java
│   │       └── main
│   │           └── com
│   │               └── blog
│   │                   ├── BlogApplication.class
│   │                   ├── domain
│   │                   │   ├── auth
│   │                   │   │   ├── controller
│   │                   │   │   │   ├── AuthController.class
│   │                   │   │   │   └── OAuth2Controller.class
│   │                   │   │   ├── dto
│   │                   │   │   │   ├── ResponseMessage.class
│   │                   │   │   │   ├── requests
│   │                   │   │   │   │   ├── AccessTokenReissuePostRequest.class
│   │                   │   │   │   │   ├── LoginPostRequest.class
│   │                   │   │   │   │   ├── OAuth2RedirectRequest.class
│   │                   │   │   │   │   └── RegisterPostRequest.class
│   │                   │   │   │   └── responses
│   │                   │   │   │       ├── AccessTokenReissuePostResponse.class
│   │                   │   │   │       ├── LoginPostResponse.class
│   │                   │   │   │       ├── OAuth2ProviderResponse.class
│   │                   │   │   │       └── RegisterPostResponse.class
│   │                   │   │   ├── entity
│   │                   │   │   │   └── enums
│   │                   │   │   │       └── OAuthProvider.class
│   │                   │   │   ├── exception
│   │                   │   │   │   ├── InvalidTokenException.class
│   │                   │   │   │   ├── JwtClaimNotBeNullException.class
│   │                   │   │   │   ├── LoginFailureException.class
│   │                   │   │   │   ├── NotFoundAccessTokenException.class
│   │                   │   │   │   ├── NotFoundRefreshTokenException.class
│   │                   │   │   │   └── OAuth2RegisterFailureException.class
│   │                   │   │   └── service
│   │                   │   │       ├── AuthService.class
│   │                   │   │       └── OAuth2Service.class
│   │                   │   ├── board
│   │                   │   │   ├── application
│   │                   │   │   │   ├── dto
│   │                   │   │   │   │   ├── PostCreateDto.class
│   │                   │   │   │   │   ├── PostReadAllResponse$PostReadAllResponseBuilder.class
│   │                   │   │   │   │   ├── PostReadAllResponse.class
│   │                   │   │   │   │   ├── PostReadResponse$PostReadResponseBuilder.class
│   │                   │   │   │   │   ├── PostReadResponse.class
│   │                   │   │   │   │   └── PostUpdateDto.class
│   │                   │   │   │   └── usecase
│   │                   │   │   │       └── PostManageUsecase.class
│   │                   │   │   ├── domain
│   │                   │   │   │   ├── entity
│   │                   │   │   │   │   ├── Post$PostBuilder.class
│   │                   │   │   │   │   └── Post.class
│   │                   │   │   │   ├── repository
│   │                   │   │   │   │   └── PostRepository.class
│   │                   │   │   │   └── service
│   │                   │   │   │       ├── PostDeleteService.class
│   │                   │   │   │       ├── PostGetService.class
│   │                   │   │   │       ├── PostSaveService.class
│   │                   │   │   │       ├── PostUpdateService.class
│   │                   │   │   │       └── PostValidateService.class
│   │                   │   │   ├── exception
│   │                   │   │   │   ├── PostAccessDeniedException.class
│   │                   │   │   │   └── PostNotFoundException.class
│   │                   │   │   └── presentation
│   │                   │   │       ├── PostController.class
│   │                   │   │       └── constant
│   │                   │   │           └── ResponseMessage.class
│   │                   │   ├── comment
│   │                   │   │   ├── application
│   │                   │   │   │   ├── dto
│   │                   │   │   │   │   ├── CommentCreateDto.class
│   │                   │   │   │   │   ├── CommentGetDto$CommentGetDtoBuilder.class
│   │                   │   │   │   │   ├── CommentGetDto.class
│   │                   │   │   │   │   └── CommentUpdateDto.class
│   │                   │   │   │   └── usecase
│   │                   │   │   │       └── CommentManageUsecase.class
│   │                   │   │   ├── domain
│   │                   │   │   │   ├── entity
│   │                   │   │   │   │   ├── Comment$CommentBuilder.class
│   │                   │   │   │   │   └── Comment.class
│   │                   │   │   │   ├── repository
│   │                   │   │   │   │   └── CommentRepository.class
│   │                   │   │   │   └── service
│   │                   │   │   │       ├── CommentDeleteService.class
│   │                   │   │   │       ├── CommentGetService.class
│   │                   │   │   │       ├── CommentSaveService.class
│   │                   │   │   │       ├── CommentUpdateService.class
│   │                   │   │   │       └── CommentValidateService.class
│   │                   │   │   ├── exception
│   │                   │   │   │   ├── CommentAccessDeniedException.class
│   │                   │   │   │   └── CommentNotFoundException.class
│   │                   │   │   └── presentation
│   │                   │   │       ├── CommentController.class
│   │                   │   │       └── constant
│   │                   │   │           └── ResponseMessage.class
│   │                   │   └── user
│   │                   │       ├── application
│   │                   │       │   └── dto
│   │                   │       │       ├── request
│   │                   │       │       │   ├── NicknamePatchRequest.class
│   │                   │       │       │   ├── PasswordPatchRequest.class
│   │                   │       │       │   └── ProfilePicturePatchRequest.class
│   │                   │       │       └── response
│   │                   │       │           └── UserGetResponse.class
│   │                   │       ├── domain
│   │                   │       │   ├── entity
│   │                   │       │   │   ├── User$UserBuilder.class
│   │                   │       │   │   └── User.class
│   │                   │       │   ├── repository
│   │                   │       │   │   └── UserRepository.class
│   │                   │       │   └── service
│   │                   │       │       ├── UserGetService.class
│   │                   │       │       └── UserService.class
│   │                   │       ├── exception
│   │                   │       │   ├── EmailDuplicateException.class
│   │                   │       │   └── UserNotFoundException.class
│   │                   │       └── presentation
│   │                   │           ├── UserController.class
│   │                   │           └── constant
│   │                   │               └── ResponseMessage.class
│   │                   └── global
│   │                       ├── common
│   │                       │   ├── auth
│   │                       │   │   ├── MemberContext.class
│   │                       │   │   ├── MemberExtractor.class
│   │                       │   │   ├── TokenMemberInfo$TokenMemberInfoBuilder.class
│   │                       │   │   ├── TokenMemberInfo.class
│   │                       │   │   ├── annotations
│   │                       │   │   │   └── UseGuards.class
│   │                       │   │   ├── aop
│   │                       │   │   │   └── UseGuardsAspect.class
│   │                       │   │   └── guards
│   │                       │   │       ├── Guard.class
│   │                       │   │       └── MemberGuard.class
│   │                       │   ├── dto
│   │                       │   │   └── ResponseDto.class
│   │                       │   ├── exception
│   │                       │   │   ├── ApplicationException.class
│   │                       │   │   ├── ExceptionDTO.class
│   │                       │   │   └── GlobalExceptionHandler.class
│   │                       │   ├── oauth
│   │                       │   │   ├── MemberInfoFromProviders.class
│   │                       │   │   ├── OAuth2AuthExecutor.class
│   │                       │   │   ├── OAuth2ProviderResponse.class
│   │                       │   │   ├── clients
│   │                       │   │   │   ├── OAuth2ProviderClient.class
│   │                       │   │   │   └── kakao
│   │                       │   │   │       ├── KakaoInfo$KakaoAccount.class
│   │                       │   │   │       ├── KakaoInfo$Profile.class
│   │                       │   │   │       ├── KakaoInfo$Properties.class
│   │                       │   │   │       ├── KakaoInfo.class
│   │                       │   │   │       └── KakaoProviderClient.class
│   │                       │   │   └── url_builder
│   │                       │   │       ├── KakaoURLBuilder.class
│   │                       │   │       └── OAuth2URLBuilder.class
│   │                       │   └── utils
│   │                       │       └── jwt
│   │                       │           ├── JwtAuthenticator.class
│   │                       │           ├── JwtClaim.class
│   │                       │           ├── JwtExtractor.class
│   │                       │           └── JwtProvider.class
│   │                       └── config
│   │                           ├── oauth
│   │                           │   └── KakaoOAuthConfigProperties.class
│   │                           ├── properties
│   │                           │   └── AppConfigProperties.class
│   │                           ├── security
│   │                           │   ├── BCryptoConfig.class
│   │                           │   └── CorsConfig.class
│   │                           └── swagger
│   │                               └── SwaggerConfig.class
│   ├── generated
│   │   └── sources
│   │       ├── annotationProcessor
│   │       │   └── java
│   │       │       └── main
│   │       └── headers
│   │           └── java
│   │               └── main
│   ├── reports
│   │   └── problems
│   │       └── problems-report.html
│   ├── resources
│   │   └── main
│   │       ├── application-local.yml
│   │       └── application.yml

```

3. 아래 API 요구사항은 API URI를 포함하고 있습니다. 반드시 URI를 지켜서 구현해주세요

`ex) GET posts/all`


## API 요구사항

### 회원가입(/auth)

- 사용자는 이메일 주소 또는 카카오 OAuth를 통해 회원가입을 진행할 수 있어야 합니다.
- 사용자는 비밀번호를 생성하여 회원가입을 진행할 수 있어야 합니다.
- 사용자는 프로필사진을 등록하며 회원가입을 진행할 수 있어야합니다(이메일 로그인에 한함)
- 사용자가 입력한 이메일 주소와 닉네임은 시스템에 이미 등록되어 있지 않아야 합니다.

### 로그인(/login)

- 사용자는 등록한 이메일 주소 또는 카카오 로그인을 이용하여 로그인할 수 있어야 합니다.
- (토큰 방식으로 구현시) refresh token을 통해 새로운 access token을 발급받을 수 있어야 합니다.

### 게시물 (/posts)

- 사용자는 로그인을 하지 않고도 게시물을 조회할 수 있어야 합니다.
- 사용자는 로그인을 진행해야 게시물을 작성할 수 있어야 합니다.
- 사용자는 자신의 게시물만 수정, 삭제할 수 있어야 합니다.
- 게시물은 페이지네이션이 가능해야 합니다.
- 게시물의 목록 조회와 게시물 내용을 보는 API는 별도로 구현되어야 합니다.
- 게시물 조회시 댓글도 모두 조회할 수 있어야 합니다.

### 댓글(/comments)

- 사용자는 로그인을 하지 않고도 댓글을 확인할 수 있어야 합니다.
- 사용자는 자신의 댓글만 수정, 삭제할 수 있어야 합니다.
- 댓글에는 댓글을 달수 없습니다(단 원하는 경우 구현해도 괜찮습니다)

### 유저(/users)

- 사용자는 닉네임, 비밀번호, 프로필 사진을 변경할 수 있어야 합니다.
- 사용자는 자신의 정보를 조회할 수 있어야 합니다.

### 참고

- 모든 댓글, 게시물은 조회시 자신의 소유 여부를 응답으로 반환해야 합니다.
- 카카오 로그인과 이메일 로그인은 별도의 API로 구현되어야 합니다.

