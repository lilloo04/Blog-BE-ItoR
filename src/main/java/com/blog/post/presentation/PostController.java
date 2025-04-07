package com.blog.post.presentation;

import com.blog.post.domain.PostService;
import com.blog.post.presentation.dto.PostRequest;
import com.blog.post.presentation.dto.PostResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시물 업로드
    @PostMapping
    public ResponseEntity<PostResponse> createPost(
            @RequestBody PostRequest request,
            @RequestHeader("Authorization") String token
    ) {
        Integer userId = extractUserIdFromToken(token);
        PostResponse response = postService.createPost(request, userId);
        return ResponseEntity.ok(response);
    }

    // 게시물 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Integer postId) {
        PostResponse response = postService.getPostById(postId);
        return ResponseEntity.ok(response);
    }

    // 게시물 수정
    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable Integer postId,
            @RequestBody PostRequest request,
            @RequestHeader("Authorization") String token
    ) {
        Integer userId = extractUserIdFromToken(token);
        PostResponse response = postService.updatePost(postId, request, userId);
        return ResponseEntity.ok(response);
    }

    // 게시물 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Integer postId,
            @RequestHeader("Authorization") String token
    ) {
        Integer userId = extractUserIdFromToken(token);
        postService.deletePost(postId, userId);
        return ResponseEntity.ok().build();
    }

    // 토큰에서 userId 파싱
    private Integer extractUserIdFromToken(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        return Integer.parseInt(com.blog.token.infrastructure.JwtTokenProvider
                .parseUserId(token));
    }
}
