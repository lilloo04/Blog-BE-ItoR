package com.blog.comment.presentation;

import com.blog.comment.domain.CommentService;
import com.blog.comment.presentation.dto.CommentCreateRequest;
import com.blog.comment.presentation.dto.CommentResponse;
import com.blog.comment.presentation.dto.CommentUpdateRequest;
import com.blog.token.infrastructure.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post/{postId}/comment")
public class CommentController {

    private final CommentService commentService;
    private final JwtTokenProvider jwtTokenProvider;

    public CommentController(CommentService commentService, JwtTokenProvider jwtTokenProvider) {
        this.commentService = commentService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 댓글 등록
    @PostMapping
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable Integer postId,
            @RequestBody CommentCreateRequest request,
            @RequestHeader("Authorization") String token
    ) {
        Integer userId = jwtTokenProvider.extractUserId(token);
        CommentResponse response = commentService.createComment(postId, request, userId);
        return ResponseEntity.ok(response);
    }

    // 댓글 조회
    @GetMapping
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Integer postId) {
        List<CommentResponse> responses = commentService.getComments(postId);
        return ResponseEntity.ok(responses);
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable Integer postId,
            @PathVariable Integer commentId,
            @RequestBody CommentUpdateRequest request,
            @RequestHeader("Authorization") String token
    ) {
        Integer userId = jwtTokenProvider.extractUserId(token);
        CommentResponse response = commentService.updateComment(postId, commentId, request, userId);
        return ResponseEntity.ok(response);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Integer postId,
            @PathVariable Integer commentId,
            @RequestHeader("Authorization") String token
    ) {
        Integer userId = jwtTokenProvider.extractUserId(token);
        commentService.deleteComment(postId, commentId, userId);
        return ResponseEntity.ok().build();
    }
}
